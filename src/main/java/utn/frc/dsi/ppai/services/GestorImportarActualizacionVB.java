package utn.frc.dsi.ppai.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.frc.dsi.ppai.dtos.BodegaDto;
import utn.frc.dsi.ppai.dtos.VinoDto;
import utn.frc.dsi.ppai.dtos.responses.ItemResumenActualizacionBodegaDto;
import utn.frc.dsi.ppai.dtos.responses.ResumenActualizacionDto;
import utn.frc.dsi.ppai.models.BodegaEntity;
import utn.frc.dsi.ppai.models.EnofiloEntity;
import utn.frc.dsi.ppai.models.TipoUvaEntity;
import utn.frc.dsi.ppai.models.VinoEntity;
import utn.frc.dsi.ppai.repositories.BodegaRepository;
import utn.frc.dsi.ppai.repositories.TipoUvaRepository;
import utn.frc.dsi.ppai.repositories.VinoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class GestorImportarActualizacionVB implements SujetoNotificador {
    private List<String> bodegasSeleccionadas;

    private final TipoUvaRepository tipoUvaRepository;
    private final BodegaRepository bodegaRepository;
    private final VinoRepository vinoRepository;
    private final InterfazImportarActualizacionVB importadorActualizacion;

    private List<EnofiloEntity> enofilos = new ArrayList<>();

    private List<ObservadorNotificacionesPush> observadores = new ArrayList<>();

    @Autowired
    public GestorImportarActualizacionVB(BodegaRepository bodegaRepository, VinoRepository vinoRepository, InterfazImportarActualizacionVB importadorActualizacion, TipoUvaRepository tipoUvaRepository) {
        this.bodegaRepository = bodegaRepository;
        this.vinoRepository = vinoRepository;
        this.importadorActualizacion = importadorActualizacion;
        this.tipoUvaRepository = tipoUvaRepository;
    }


    public List<BodegaDto> buscarBodegasConActualizacion() {
        // Convierte el Iterable a un Stream para aplicar filtrado y mapeo
        return StreamSupport.stream(bodegaRepository.findAll().spliterator(), false)
                .filter(BodegaEntity::tieneActualizacion)
                .map(bodega -> new BodegaDto(bodega))
                .collect(Collectors.toList());
    }

    public ResumenActualizacionDto tomarBodegasSeleccionadas(List<String> bodegasSeleccionadas){
        this.bodegasSeleccionadas = bodegasSeleccionadas;
        if(this.verificarSeleccionUnica()){
            return this.actualizarDatosBodega(this.bodegasSeleccionadas.get(0));
        } else {
            throw new RuntimeException("Se selecciono mas de una bodega.");
        }
    }

    private VinoEntity crearVino(BodegaEntity bodega, VinoDto actualizacion) {
        Optional<TipoUvaEntity> tipoUvaExistente = tipoUvaRepository.findByNombre(actualizacion.getVarietalDto().getTipoUvaDto().getNombre());
        TipoUvaEntity tipoUva = tipoUvaExistente.orElseGet(() -> new TipoUvaEntity(actualizacion.getVarietalDto().getTipoUvaDto().getNombre(),
                actualizacion.getVarietalDto().getTipoUvaDto().getDescripcion()));


        VinoEntity nuevoVino = new VinoEntity();
        nuevoVino.setAniada(actualizacion.getAniada());
        nuevoVino.setImgEtiqueta(actualizacion.getImgEtiqueta());
        nuevoVino.setNombre(actualizacion.getNombre());
        nuevoVino.setNotaDeCata(actualizacion.getNotaDeCata());
        nuevoVino.setPrecio(actualizacion.getPrecio());
        nuevoVino.setBodega(bodega);
        nuevoVino.crearVarietal(actualizacion.getVarietalDto(), tipoUva);
        vinoRepository.save(nuevoVino);
        return nuevoVino;
    }

    private void actualiarVinoExistente(VinoEntity vino, VinoDto actualizacion){
        vino.actualizarDatosVino(actualizacion);
        vinoRepository.save(vino);
    }

    private boolean verificarSeleccionUnica(){
        return this.bodegasSeleccionadas.size() == 1;
    }

    private ResumenActualizacionDto actualizarDatosBodega(String nombreBodega){
        List<ItemResumenActualizacionBodegaDto> itemsResumenActualizacion = new ArrayList<>();

        BodegaDto actualizaciones = importadorActualizacion.solicitarActualizacionAPI(nombreBodega);
        BodegaEntity bodegaExistente = bodegaRepository.findByNombre(nombreBodega)
                .orElseThrow(() -> new IllegalArgumentException("La bodega con nombre " + nombreBodega + " no existe en la base de datos."));

        actualizaciones.getVinos().forEach(actualizacion -> {
            // Busca en la base de datos si existe un vino con el mismo nombre
            Optional<VinoEntity> vinoExistente = vinoRepository.findByNombre(actualizacion.getNombre());

            if (vinoExistente.isPresent()) {
                // Flujo para cuando el vino ya existe en la base de datos
                // Es una ACTUALIZACION
                VinoEntity vino = vinoExistente.get();
                this.actualiarVinoExistente(vino, actualizacion);
                itemsResumenActualizacion.add(new ItemResumenActualizacionBodegaDto(vino, "actualizacion"));
            } else {
                // Flujo para cuando el vino no existe en la base de datos
                // Es una CREACION
                VinoEntity nuevoVino = this.crearVino(bodegaExistente, actualizacion);
                itemsResumenActualizacion.add(new ItemResumenActualizacionBodegaDto(nuevoVino, "creacion"));
            }
        });

        bodegaExistente.actualizarFechaUltimaActualizacion();
        bodegaRepository.save(bodegaExistente);
        return new ResumenActualizacionDto(bodegaExistente, itemsResumenActualizacion);
    }

    /*
    public List<EnofiloEntity> buscarSeguidoresDeBodega(BodegaEntity bodega) {
        List<EnofiloEntity> seguidores = new ArrayList<>();
        // Iterar sobre todos los enófilos registrados
        for (EnofiloEntity enofilo : enofilos) {
            // Verificar si el enófilo sigue la bodega pasada como parámetro
            if (enofilo.sigueABodega(bodega)) {
                seguidores.add(enofilo);
            }
        }
        return seguidores;
    }

    public List<EnofiloEntity> buscarSeguidoresDeBodega(List<String> bodegasSeleccionadas) {
        this.bodegasSeleccionadas = bodegasSeleccionadas;
        if (this.bodegasSeleccionadas.size() != 1) {
            throw new IllegalArgumentException("Se debe seleccionar exactamente una bodega.");
        }

        String nombreBodega = this.bodegasSeleccionadas.get(0);
        BodegaEntity bodega = bodegaRepository.findByNombre(nombreBodega)
                .orElseThrow(() -> new IllegalArgumentException("Bodega no encontrada: " + nombreBodega));

        return buscarSeguidoresDeBodega(bodega);
    }
    */

    // PATRON OBSERVER

    @Override
    public void suscribir(List<ObservadorNotificacionesPush> obs) {
        observadores.addAll(obs);

    }

    @Override
    public void quitar(List<ObservadorNotificacionesPush> obs) {
        observadores.removeAll(obs);

    }

    @Override
    public void notificar() {
        // detalles del vino
        // nombreVino
        // anada
        // precio
        // nombreBodega
        // nombreVarietal
        // fecha
        // destinatarios

        for (ObservadorNotificacionesPush obs : observadores) {
            // obs.actualizar(nombreVino, anada, precio, nombreBodega, nombreVarietal, fecha, destinatarios);
        }
    }


}

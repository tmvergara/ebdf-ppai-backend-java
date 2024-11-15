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
import utn.frc.dsi.ppai.repositories.EnofiloRepository;
import utn.frc.dsi.ppai.repositories.TipoUvaRepository;
import utn.frc.dsi.ppai.repositories.VinoRepository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class GestorImportarActualizacionVB implements SujetoNotificador {
    private final EnofiloRepository enofiloRepository;
    private final TipoUvaRepository tipoUvaRepository;
    private final BodegaRepository bodegaRepository;
    private final VinoRepository vinoRepository;
    private final InterfazImportarActualizacionVB importadorActualizacion;

    private List<String> bodegasSeleccionadas;
    private List<VinoEntity> vinosActualizados = new ArrayList<>();
    private List<ObservadorNotificacionesPush> observadores = new ArrayList<>();

    @Autowired
    public GestorImportarActualizacionVB(BodegaRepository bodegaRepository, VinoRepository vinoRepository, InterfazImportarActualizacionVB importadorActualizacion, TipoUvaRepository tipoUvaRepository, EnofiloRepository enofiloRepository) {
        this.bodegaRepository = bodegaRepository;
        this.vinoRepository = vinoRepository;
        this.importadorActualizacion = importadorActualizacion;
        this.tipoUvaRepository = tipoUvaRepository;
        this.enofiloRepository = enofiloRepository;
    }

    public List<BodegaDto> buscarBodegasConActualizacion() {
        // Convierte el Iterable a un Stream para aplicar filtrado y mapeo
        return StreamSupport.stream(this.bodegaRepository.findAll().spliterator(), false)
                .filter(BodegaEntity::tieneActualizacion)
                .map(BodegaDto::new)
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
        Optional<TipoUvaEntity> tipoUvaExistente = this.tipoUvaRepository.findByNombre(actualizacion.getVarietalDto().getTipoUvaDto().getNombre());
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
        this.vinoRepository.save(nuevoVino);
        return nuevoVino;
    }

    private void actualizarVinoExistente(VinoEntity vino, VinoDto actualizacion){
        vino.actualizarDatosVino(actualizacion);
        this.vinoRepository.save(vino);
    }

    private boolean verificarSeleccionUnica(){
        return this.bodegasSeleccionadas.size() == 1;
    }

    private ResumenActualizacionDto actualizarDatosBodega(String nombreBodega){
        List<ItemResumenActualizacionBodegaDto> itemsResumenActualizacion = new ArrayList<>();

        BodegaDto actualizaciones = this.importadorActualizacion.solicitarActualizacionAPI(nombreBodega);
        BodegaEntity bodegaExistente = this.bodegaRepository.findByNombre(nombreBodega)
                .orElseThrow(() -> new IllegalArgumentException("La bodega con nombre " + nombreBodega + " no existe en la base de datos."));

        actualizaciones.getVinos().forEach(actualizacion -> {
            // Busca en la base de datos si existe un vino con el mismo nombre y bodega
            Optional<VinoEntity> vinoExistente = this.vinoRepository.findByNombreAndBodega(
                    actualizacion.getNombre(),
                    bodegaExistente
            );

            if (vinoExistente.isPresent()) {
                // Flujo para cuando el vino ya existe en la base de datos
                // Es una ACTUALIZACION
                VinoEntity vino = vinoExistente.get();
                this.actualizarVinoExistente(vino, actualizacion);
                itemsResumenActualizacion.add(new ItemResumenActualizacionBodegaDto(vino, "actualizacion"));
                this.vinosActualizados.add(vino);
            } else {
                // Flujo para cuando el vino no existe en la base de datos
                // Es una CREACION
                VinoEntity nuevoVino = this.crearVino(bodegaExistente, actualizacion);
                itemsResumenActualizacion.add(new ItemResumenActualizacionBodegaDto(nuevoVino, "creacion"));
                this.vinosActualizados.add(nuevoVino);
            }
        });

        bodegaExistente.actualizarFechaUltimaActualizacion();
        this.bodegaRepository.save(bodegaExistente);

        // Notificar a los enófilos después de procesar todas las actualizaciones
        this.notificarEnofilos();
        return new ResumenActualizacionDto(bodegaExistente, itemsResumenActualizacion);
    }

    /*
        Este metodo es ineficiente, se pdoria hacer la consulta directamente a la base de datos
        pero se resuevle de esta forma para respetar los diagramas.
     */
    private List<String> buscarSeguidoresDeBodega(BodegaEntity bodega) {
        Iterable<EnofiloEntity> enofilos = this.enofiloRepository.findAll();
        List<EnofiloEntity> seguidores = new ArrayList<>();

        // Iterar sobre todos los enófilos registrados
        for (EnofiloEntity enofilo : enofilos) {
            // Verificar si el enófilo sigue la bodega pasada como parámetro
            if (enofilo.sigueABodega(bodega)) {
                seguidores.add(enofilo);
            }
        }

        // Iterar sobre los seguidores para obtener el nombre de usuario, de nuevo, altamente ineficicente pero esto dice el diagrama.
        List<String> nombreUsuarioSeguidores = new ArrayList<>();
        for (EnofiloEntity seguidor : seguidores){
            nombreUsuarioSeguidores.add(seguidor.getUsuario().getNombre());
        }
        System.out.println(nombreUsuarioSeguidores);
        return nombreUsuarioSeguidores;
    }

    // PATRON OBSERVER
    public void notificarEnofilos() {
        List<ObservadorNotificacionesPush> notificacionesPush = new ArrayList<>();
        notificacionesPush.add(new InterfazNotificacionesPush());
        this.suscribir(notificacionesPush);
        this.notificar();
    }

    @Override
    public void suscribir(List<ObservadorNotificacionesPush> obs) {
        this.observadores.addAll(obs);
    }

    @Override
    public void quitar(List<ObservadorNotificacionesPush> obs) {
        this.observadores.removeAll(obs);
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

        for (ObservadorNotificacionesPush obs : this.observadores) {
            System.out.println("OBS");
            for (VinoEntity vino : this.vinosActualizados) {
                System.out.println(vino.getBodega());
                obs.actualizar( vino.getNombre(),
                                vino.getAniada(),
                                vino.getPrecio(),
                                vino.getBodega().getNombre(),
                                vino.getVarietal().getTipoUva().getNombre(),
                                new Date(),
                                this.buscarSeguidoresDeBodega(vino.getBodega()));
            }
        }
    }


}

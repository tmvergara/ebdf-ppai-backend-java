package utn.frc.dsi.ppai.services;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.frc.dsi.ppai.dtos.BodegaDto;
import utn.frc.dsi.ppai.dtos.VinoDto;
import utn.frc.dsi.ppai.dtos.responses.ItemResumenActualizacionDto;
import utn.frc.dsi.ppai.dtos.responses.ResumenActualizacionBodegaDto;
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

    public ResumenActualizacionDto tomarBodegasSeleccionadas(List<String> bodegasSeleccionadas) throws ServiceException {
        this.bodegasSeleccionadas = bodegasSeleccionadas;
        if(this.verificarSeleccionUnica()){
            // Curso principal.
            return new ResumenActualizacionDto(this.actualizarDatosBodega(this.bodegasSeleccionadas.get(0)));
        } else {
            // Curso alternativo 1. Se selecciono mas de una bodega.
            List<ResumenActualizacionBodegaDto> bodegasActualizadas = this.bodegasSeleccionadas.stream().map(this::actualizarDatosBodega).toList();
            return new ResumenActualizacionDto(bodegasActualizadas);
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

    private ResumenActualizacionBodegaDto actualizarDatosBodega(String nombreBodega) throws ServiceException {
        List<ItemResumenActualizacionDto> itemsResumenActualizacion = new ArrayList<>();

        // Simula que se obtienen los datos de la API externa de cada bodega.
        BodegaDto actualizaciones = this.importadorActualizacion.solicitarActualizacionAPI(nombreBodega);

        // Busca la bodega seleccionada de la BD.
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
                itemsResumenActualizacion.add(new ItemResumenActualizacionDto(vino, "actualizacion"));
                this.vinosActualizados.add(vino);
            } else {
                // Flujo para cuando el vino no existe en la base de datos
                // Es una CREACION
                VinoEntity nuevoVino = this.crearVino(bodegaExistente, actualizacion);
                itemsResumenActualizacion.add(new ItemResumenActualizacionDto(nuevoVino, "creacion"));
                this.vinosActualizados.add(nuevoVino);
            }
        });

        bodegaExistente.actualizarFechaUltimaActualizacion();
        this.bodegaRepository.save(bodegaExistente);

        // Notificar a los enófilos después de procesar todas las actualizaciones
        this.notificarEnofilos();
        return new ResumenActualizacionBodegaDto(bodegaExistente, itemsResumenActualizacion);
    }

    /*
        Este metodo es ineficiente, se podria hacer la consulta directamente a la base de datos,
        pero se resuelve de esta forma para respetar los diagramas.
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

        // Iterar sobre los seguidores para obtener el nombre de usuario, de nuevo, altamente ineficicente, pero esto dice el diagrama.
        List<String> nombreUsuarioSeguidores = new ArrayList<>();
        for (EnofiloEntity seguidor : seguidores){
            nombreUsuarioSeguidores.add(seguidor.getUsuario().getNombre());
        }
        System.out.println("Destinatarios: " + nombreUsuarioSeguidores);
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
        for (ObservadorNotificacionesPush obs : this.observadores) {
            for (VinoEntity vino : this.vinosActualizados) {
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

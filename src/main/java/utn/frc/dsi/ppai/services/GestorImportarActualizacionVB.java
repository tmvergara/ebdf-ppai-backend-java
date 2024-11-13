package utn.frc.dsi.ppai.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.frc.dsi.ppai.dtos.BodegaDto;
import utn.frc.dsi.ppai.dtos.VinoDto;
import utn.frc.dsi.ppai.models.BodegaEntity;
import utn.frc.dsi.ppai.repositories.BodegaRepository;
import utn.frc.dsi.ppai.repositories.VinoRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class GestorImportarActualizacionVB implements SujetoNotificador {

    private final BodegaRepository bodegaRepository;
    private final VinoRepository vinoRepository;

    @Autowired
    public GestorImportarActualizacionVB(BodegaRepository bodegaRepository, VinoRepository vinoRepository) {
        this.bodegaRepository = bodegaRepository;
        this.vinoRepository = vinoRepository;
    }

    
    public List<BodegaDto> buscarBodegasConActualizacion() {
        // Convierte el Iterable a un Stream para aplicar filtrado y mapeo
        return StreamSupport.stream(bodegaRepository.findAll().spliterator(), false)
                .filter(BodegaEntity::tieneActualizacion)
                .map(bodega -> new BodegaDto(bodega))
                .collect(Collectors.toList());
    }

    private void crearVino(VinoDto vinoActualizacion) {
//        Vino nuevoVino = new Vino(
//                vinoActualizacion.getAniada(),
//                vinoActualizacion.getImgEtiqueta(),
//                vinoActualizacion.getNombre(),
//                vinoActualizacion.getNotaDeCata(),
//                vinoActualizacion.getPrecio(),
//        )
        nuevoVino.crearVarietal(vinoActualizacion.getVarietal());
        vinoRepository.save(nuevoVino);
    }











    // PATRON OBSERVER

    @Override
    public void suscribir(List<ObservadorNotificacionesPush> obs) {

    }

    @Override
    public void quitar(List<ObservadorNotificacionesPush> obs) {

    }

    @Override
    public void notificar() {

    }
}

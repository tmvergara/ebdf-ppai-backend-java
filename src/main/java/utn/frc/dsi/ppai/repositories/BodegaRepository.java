package utn.frc.dsi.ppai.repositories;

import org.springframework.data.repository.CrudRepository;
import utn.frc.dsi.ppai.models.BodegaEntity;
import utn.frc.dsi.ppai.models.VinoEntity;

import java.util.Optional;

public interface BodegaRepository extends CrudRepository<BodegaEntity, Integer> {
    Optional<BodegaEntity> findByNombre(String nombre);
}

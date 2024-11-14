package utn.frc.dsi.ppai.repositories;

import org.springframework.data.repository.CrudRepository;
import utn.frc.dsi.ppai.models.VinoEntity;

import java.util.Optional;

public interface VinoRepository extends CrudRepository<VinoEntity, Integer> {
    Optional<VinoEntity> findByNombre(String nombre);
}

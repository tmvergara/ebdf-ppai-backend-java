package utn.frc.dsi.ppai.repositories;

import org.springframework.data.repository.CrudRepository;
import utn.frc.dsi.ppai.models.TipoUvaEntity;
import utn.frc.dsi.ppai.models.VinoEntity;

import java.util.Optional;

public interface TipoUvaRepository extends CrudRepository<TipoUvaEntity, Integer> {
    Optional<TipoUvaEntity> findByNombre(String nombre);
}

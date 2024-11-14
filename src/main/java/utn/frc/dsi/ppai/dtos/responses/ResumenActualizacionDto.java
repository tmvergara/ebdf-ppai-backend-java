package utn.frc.dsi.ppai.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import utn.frc.dsi.ppai.models.BodegaEntity;

import java.util.List;

@Data
@AllArgsConstructor
public class ResumenActualizacionDto {
    BodegaEntity bodega;
    List<ItemResumenActualizacionBodegaDto> updates;

}

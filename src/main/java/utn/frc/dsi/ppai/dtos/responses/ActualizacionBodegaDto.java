package utn.frc.dsi.ppai.dtos.responses;

import lombok.Data;
import utn.frc.dsi.ppai.dtos.VinoDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ActualizacionBodegaDto {
    private Map<String, List<VinoDto>> bodegas = new HashMap<>();
}

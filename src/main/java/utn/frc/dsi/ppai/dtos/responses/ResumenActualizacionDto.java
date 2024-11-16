package utn.frc.dsi.ppai.dtos.responses;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResumenActualizacionDto {
    private Integer cantidadBodegasActualizadas;
    private List<ResumenActualizacionBodegaDto> actualizaciones;

    public ResumenActualizacionDto(List<ResumenActualizacionBodegaDto> bodegasActualizadas){
        this.cantidadBodegasActualizadas = bodegasActualizadas.size();
        this.actualizaciones = bodegasActualizadas;
    }

    public ResumenActualizacionDto(ResumenActualizacionBodegaDto bodegaActualizada){
        this.cantidadBodegasActualizadas = 1;
        this.actualizaciones = new ArrayList<>();
        this.actualizaciones.add(bodegaActualizada);
    }
}

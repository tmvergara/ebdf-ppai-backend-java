package utn.frc.dsi.ppai.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import utn.frc.dsi.ppai.models.SiguiendoEntity;

import java.util.Date;

@Data
@AllArgsConstructor
public class SiguiendoDto {
    private Date fechaInicio;
    private Date fechaFin;
    private String bodegaNombre; // Cambiado a String para coincidir con el JSON de ejemplo

    public SiguiendoDto(SiguiendoEntity siguiendo) {
        this.fechaInicio = siguiendo.getFechaInicio();
        this.fechaFin = siguiendo.getFechaFin();
        this.bodegaNombre = siguiendo.getBodega().getNombre();
    }
}


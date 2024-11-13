package utn.frc.dsi.ppai.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import utn.frc.dsi.ppai.models.MaridajeEntity;

@Data
@AllArgsConstructor
public class MaridajeDto {
    private String nombre;
    private String descripcion;

    public MaridajeDto(MaridajeEntity maridaje) {
        this.nombre = maridaje.getNombre();
        this.descripcion = maridaje.getDescripcion();
    }
}


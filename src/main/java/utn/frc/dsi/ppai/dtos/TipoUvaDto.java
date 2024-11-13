package utn.frc.dsi.ppai.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import utn.frc.dsi.ppai.models.TipoUvaEntity;

@Data
@AllArgsConstructor
public class TipoUvaDto {
    private String nombre;
    private String descripcion;

    public TipoUvaDto(TipoUvaEntity tipoUva) {
        this.nombre = tipoUva.getNombre();
        this.descripcion = tipoUva.getDescripcion();
    }
}

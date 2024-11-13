package utn.frc.dsi.ppai.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import utn.frc.dsi.ppai.models.TipoUvaEntity;
import utn.frc.dsi.ppai.models.VarietalEntity;

@Data
@AllArgsConstructor
public class VarietalDto {
    private String descripcion;
    private int porcentajeComposicion;
    private TipoUvaDto tipoUvaDto;

    public VarietalDto(VarietalEntity varietal) {
        this.descripcion = varietal.getDescripcion();
        this.porcentajeComposicion = varietal.getPorcentajeComposicion();
        this.tipoUvaDto = new TipoUvaDto(varietal.getTipoUva());
    }
}

package utn.frc.dsi.ppai.dtos.responses;

import lombok.Data;
import utn.frc.dsi.ppai.models.VarietalEntity;
import utn.frc.dsi.ppai.models.VinoEntity;

@Data
public class ItemResumenActualizacionBodegaDto {
    private String nombre;
    private Double precio;
    private Double notaDeCata;
    private String imgEtiqueta;
    private String varietal;
    private String tipoUpdate;

    public ItemResumenActualizacionBodegaDto(String nombre, Double precio, Double notaDeCata, String imgEtiqueta, VarietalEntity varietal, String tipoUpdate) {
        this.nombre = nombre;
        this.precio = precio;
        this.notaDeCata = notaDeCata;
        this.imgEtiqueta = imgEtiqueta;
        this.varietal = varietal.getTipoUva().getNombre();
        this.tipoUpdate = tipoUpdate;
    }

    public ItemResumenActualizacionBodegaDto(VinoEntity vinoActualizado, String tipoUpdate){
        this.nombre = vinoActualizado.getNombre();
        this.precio = vinoActualizado.getPrecio();
        this.notaDeCata = vinoActualizado.getNotaDeCata();
        this.imgEtiqueta = vinoActualizado.getImgEtiqueta();
        this.varietal = vinoActualizado.getVarietal().getTipoUva().getNombre();
        this.tipoUpdate = tipoUpdate;
    }
}

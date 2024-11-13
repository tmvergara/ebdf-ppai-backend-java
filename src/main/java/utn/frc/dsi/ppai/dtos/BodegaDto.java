package utn.frc.dsi.ppai.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import utn.frc.dsi.ppai.models.BodegaEntity;

@Data
@AllArgsConstructor
public class BodegaDto {
    private Integer id;
    private String nombre;
    private String coordenadas;
    private String sitioWeb;
    private String imgLogoBodega;

    public BodegaDto(BodegaEntity bodega) {
        this.id = bodega.getId();
        this.nombre = bodega.getNombre();
        this.coordenadas = bodega.getCoordenadas();
        this.sitioWeb = bodega.getSitioWeb();
        this.imgLogoBodega = bodega.getImgLogoBodega();
    }
}

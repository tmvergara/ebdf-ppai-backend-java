package utn.frc.dsi.ppai.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import utn.frc.dsi.ppai.models.BodegaEntity;

import java.util.Date;

@Data
@AllArgsConstructor
public class BodegaDto {
    private int id;
    private String nombre;
    private String descripcion;
    private String historias;
    private int periodoActualizacion;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date ultimaActualizacion;

    private String imgLogoBodega;
    private String sitioWeb;
    private Coordenadas coordenadas;

    @Data
    public static class Coordenadas {
        @JsonProperty("lat")
        private double lat;

        @JsonProperty("lng")
        private double lng;

        public Coordenadas(double lat, double lng) {
            this.lat = lat;
            this.lng = lng;
        }
    }

    public BodegaDto(BodegaEntity bodega) {
        this.id = bodega.getId();
        this.nombre = bodega.getNombre();
        this.descripcion = bodega.getDescripcion();
        this.historias = bodega.getHistorias();
        this.periodoActualizacion = bodega.getPeriodoActualziacion();
        this.ultimaActualizacion = bodega.getUltimaActualizacion();
        this.imgLogoBodega = bodega.getImgLogoBodega();
        this.sitioWeb = bodega.getSitioWeb();
        this.coordenadas = parseCoordenadas(bodega.getCoordenadas());
    }

    private Coordenadas parseCoordenadas(String coordenadasStr) {
        if (coordenadasStr != null && coordenadasStr.contains(",")) {
            String[] partes = coordenadasStr.split(",");
            double lat = Double.parseDouble(partes[0]);
            double lng = Double.parseDouble(partes[1]);
            return new Coordenadas(lat, lng);
        }
        return null;
    }
}

package utn.frc.dsi.ppai.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utn.frc.dsi.ppai.models.BodegaEntity;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
    private List<VinoDto> vinos;

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
        this.periodoActualizacion = bodega.getPeriodoActualizacion();
        this.ultimaActualizacion = bodega.getUltimaActualizacion();
        this.imgLogoBodega = bodega.getImgLogoBodega();
        this.sitioWeb = bodega.getSitioWeb();
        this.coordenadas = parseCoordenadas(bodega.getLatitud(), bodega.getLongitud());
    }

    private Coordenadas parseCoordenadas(Double latitud, Double longitud) {
        return new Coordenadas(latitud, longitud);
    }
}

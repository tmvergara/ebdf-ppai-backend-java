package utn.frc.dsi.ppai.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Getter @Setter @NoArgsConstructor @ToString @AllArgsConstructor @EqualsAndHashCode
@Table(name = "bodega")
public class BodegaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String descripcion;
    private String historias;
    private Integer periodoActualizacion;
    private Date ultimaActualizacion;
    private String imgLogoBodega;
    private Double latitud;
    private Double longitud;
    private String sitioWeb;

    public Boolean tieneActualizacion() {
        if (ultimaActualizacion == null) {
            return true;
        }

        Calendar calendario = Calendar.getInstance();
        calendario.setTime(ultimaActualizacion);
        calendario.add(Calendar.MONTH, periodoActualizacion); // Sumar los meses

        Date fechaVencimiento = calendario.getTime();
        Date fechaActual = new Date();

        return fechaVencimiento.before(fechaActual);
    }

    public void actualizarFechaUltimaActualizacion() {
        this.ultimaActualizacion = new Date();
    }

}

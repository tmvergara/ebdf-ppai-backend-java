package utn.frc.dsi.ppai.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Calendar;
import java.util.Date;

@Getter @Setter @NoArgsConstructor @ToString @AllArgsConstructor @EqualsAndHashCode
@Entity
public class BodegaEntity {
    @Id
    private Integer id;

    private String nombre;
    private String descripcion;
    private String historias;
    private Integer periodoActualziacion;
    private Date ultimaActualizacion;
    private String imgLogoBodega;
    private String coordenadas;
    private String sitioWeb;

    public Boolean tieneActualizacion() {
        if (ultimaActualizacion == null) {
            return true;
        }

        Calendar calendario = Calendar.getInstance();
        calendario.setTime(ultimaActualizacion);
        calendario.add(Calendar.MONTH, periodoActualziacion); // Sumar los meses

        Date fechaVencimiento = calendario.getTime();
        Date fechaActual = new Date();

        return fechaVencimiento.before(fechaActual);
    }

    public void actualizarFechaUltimaActualizacion() {
        this.ultimaActualizacion = new Date();
    }

}

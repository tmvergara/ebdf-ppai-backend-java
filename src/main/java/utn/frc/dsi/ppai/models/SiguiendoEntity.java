package utn.frc.dsi.ppai.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter @Setter @NoArgsConstructor @ToString @AllArgsConstructor @EqualsAndHashCode
public class SiguiendoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Date fechaInicio;
    private Date fechaFin;

    @ManyToOne
    @JoinColumn(name = "bodega_id")
    private BodegaEntity bodega;

    @ManyToOne
    @JoinColumn(name = "enofilo_id")
    private EnofiloEntity seguido;

    public SiguiendoEntity(BodegaEntity bodega, EnofiloEntity seguido) {
        this.fechaInicio = new Date();
        this.bodega = bodega;
        this.seguido = seguido;
    }
}

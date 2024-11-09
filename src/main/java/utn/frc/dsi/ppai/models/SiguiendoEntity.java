package utn.frc.dsi.ppai.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Getter @Setter @NoArgsConstructor @ToString @AllArgsConstructor @EqualsAndHashCode
public class SiguiendoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Date fechaInicio;
    private Date fechaFin;

    private BodegaEntity bodega;

    private EnofiloEntity enofilo;

    public SiguiendoEntity(BodegaEntity bodega, EnofiloEntity enofilo) {
        this.fechaInicio = new Date();
        this.bodega = bodega;
        this.enofilo = enofilo;
    }
}

package utn.frc.dsi.ppai.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter @Setter @NoArgsConstructor @ToString @AllArgsConstructor @EqualsAndHashCode
public class VinoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer aniada;
    private String imgEtiqueta;
    private String nombre;
    private String notaDeCata;
    private float precio;

    private MaridajeEntity maridaje;

    private BodegaEntity bodega;
}

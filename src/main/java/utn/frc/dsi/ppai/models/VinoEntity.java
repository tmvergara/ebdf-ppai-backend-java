package utn.frc.dsi.ppai.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter @Setter @NoArgsConstructor @ToString @AllArgsConstructor @EqualsAndHashCode
public class VinoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer aniada;
    private String imgEtiqueta;
    private String nombre;
    private float notaDeCata;
    private float precio;

    @ManyToMany
    @JoinTable(
            name = "vino_maridaje",
            joinColumns = @JoinColumn(name = "vino_id"),
            inverseJoinColumns = @JoinColumn(name = "maridaje_id")
    )
    private List<MaridajeEntity> maridajes;

    @ManyToOne
    @JoinColumn(name = "bodega_id")
    private BodegaEntity bodega;

    @ManyToOne
    @JoinColumn(name = "varietal_id")
    private VarietalEntity varietal;

}

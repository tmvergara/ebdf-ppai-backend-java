package utn.frc.dsi.ppai.models;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @ToString @AllArgsConstructor @EqualsAndHashCode
public class VarietalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String descripcion;
    private int porcentajeComposicion;

    @ManyToOne
    @JoinColumn(name = "tipoUva_id")
    private TipoUvaEntity tipoUva;

}

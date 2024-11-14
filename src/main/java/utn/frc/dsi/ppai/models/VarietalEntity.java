package utn.frc.dsi.ppai.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @ToString @AllArgsConstructor @EqualsAndHashCode
@Table(name = "varietal")
public class VarietalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String descripcion;
    private int porcentajeComposicion;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "tipo_uva_id")
    private TipoUvaEntity tipoUva;

}

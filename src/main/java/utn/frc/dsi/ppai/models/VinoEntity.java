package utn.frc.dsi.ppai.models;

import jakarta.persistence.*;
import lombok.*;
import utn.frc.dsi.ppai.dtos.VarietalDto;
import utn.frc.dsi.ppai.dtos.VinoDto;

import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor @ToString @AllArgsConstructor @EqualsAndHashCode
@Table(name = "vino")
public class VinoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer aniada;
    private String imgEtiqueta;
    private String nombre;
    private Double notaDeCata;
    private Double precio;

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

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "varietal_id")
    private VarietalEntity varietal;

    public VinoEntity actualizarDatosVino(VinoDto actualizacion){
        this.setNombre(actualizacion.getNombre());
        this.setPrecio(actualizacion.getPrecio());
        this.setImgEtiqueta(actualizacion.getImgEtiqueta());
        this.setNotaDeCata(actualizacion.getNotaDeCata());

        return this;
    }

    public void crearVarietal(VarietalDto varietalDto, TipoUvaEntity tipoUva){
        VarietalEntity nuevoVarietal = new VarietalEntity();
        nuevoVarietal.setTipoUva(tipoUva);
        nuevoVarietal.setDescripcion(varietalDto.getDescripcion());
        nuevoVarietal.setPorcentajeComposicion(varietalDto.getPorcentajeComposicion());
        this.setVarietal(nuevoVarietal);
    }

}

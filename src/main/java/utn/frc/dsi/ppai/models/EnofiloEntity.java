package utn.frc.dsi.ppai.models;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @ToString @AllArgsConstructor @EqualsAndHashCode
public class EnofiloEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String apellido;
    private String nombre;
    private String imagenPerfil;

    @OneToOne
    @JoinColumn(name = "usuarioId")
    private UsuarioEntity usuario;

    @OneToMany(mappedBy = "enofilo")
    private SiguiendoEntity siguiendo;
}

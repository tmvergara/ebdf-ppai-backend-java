package utn.frc.dsi.ppai.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import utn.frc.dsi.ppai.models.EnofiloEntity;

@Data
@AllArgsConstructor
public class EnofiloDto {
    private String apellido;
    private String nombre;
    private String imagenPerfil;
    private String usuarioNombre;
    private SiguiendoDto siguiendoDto;

    public EnofiloDto(EnofiloEntity enofilo) {
        this.apellido = enofilo.getApellido();
        this.nombre = enofilo.getNombre();
        this.imagenPerfil = enofilo.getImagenPerfil();
        this.usuarioNombre = enofilo.getUsuario().getNombre();
        this.siguiendoDto = enofilo.getSiguiendo() != null ? new SiguiendoDto(enofilo.getSiguiendo()) : null;
    }
}


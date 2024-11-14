package utn.frc.dsi.ppai.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import utn.frc.dsi.ppai.models.EnofiloEntity;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class EnofiloDto {
    private String apellido;
    private String nombre;
    private String imagenPerfil;
    private String usuarioNombre;
    private List<SiguiendoDto> siguiendoDto;

    public EnofiloDto(EnofiloEntity enofilo) {
        this.apellido = enofilo.getApellido();
        this.nombre = enofilo.getNombre();
        this.imagenPerfil = enofilo.getImagenPerfil();
        this.usuarioNombre = enofilo.getUsuario().getNombre();
        this.siguiendoDto = enofilo.getSiguiendo().stream().map(siguiendoEntity -> new SiguiendoDto(siguiendoEntity.getFechaInicio(), siguiendoEntity.getFechaFin(), siguiendoEntity.getBodega().getNombre())).collect(Collectors.toList());
    }
}


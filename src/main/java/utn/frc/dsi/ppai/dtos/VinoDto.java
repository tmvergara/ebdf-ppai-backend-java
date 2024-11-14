package utn.frc.dsi.ppai.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import utn.frc.dsi.ppai.models.VinoEntity;

@Data
@AllArgsConstructor
public class VinoDto {
    private int id;
    private String nombre;
    private int aniada;
    private VarietalDto varietalDto;
    private Double precio;
    private Double notaDeCata;
    private String imgEtiqueta;
    //    private List<MaridajeDto> maridajeDto;
    //    private MaridajeDto maridajeDto;

    public VinoDto(VinoEntity vino) {
        this.id = vino.getId();
        this.nombre = vino.getNombre();
        this.aniada = vino.getAniada();
        this.varietalDto = new VarietalDto(vino.getVarietal());
        this.precio = vino.getPrecio();
        this.notaDeCata = vino.getNotaDeCata();
        this.imgEtiqueta = vino.getImgEtiqueta();
        //    this.maridajeDto = new MaridajeDto(vino.getMaridajes());
        //    this.maridajeDto = vino.getMaridajes().forEach(MaridajeDto::new);

    }
}

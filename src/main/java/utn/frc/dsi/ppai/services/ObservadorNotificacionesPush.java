package utn.frc.dsi.ppai.services;

import java.util.Date;
import java.util.List;

public interface ObservadorNotificacionesPush {
    void actualizar(String nombreVino, Integer aniada, Double precio,
                    String nombreBodega, String nombreVarietal, Date
                            fecha, List<String> destinatarios);
}

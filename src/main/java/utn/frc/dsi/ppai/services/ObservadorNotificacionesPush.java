package utn.frc.dsi.ppai.services;

import java.util.Date;

public interface ObservadorNotificacionesPush {
    void actualizar(String nombreVino, Integer aniada, Double precio,
                    String nombreBodega, String nombreVarietal, Date
                            fecha, String[] destinatarios);
}

package utn.frc.dsi.ppai.services;

import utn.frc.dsi.ppai.models.EnofiloEntity;

import java.util.Date;
import java.util.List;

public class InterfazNotificacionesPush implements ObservadorNotificacionesPush {

    @Override
    public void actualizar(String nombreVino, Integer aniada, Double precio, String nombreBodega, String nombreVarietal, Date fecha, List<String> destinatarios) {
        String mensaje = String.format(
                "Actualización de vino: %s (Añada %d) de bodega %s\n" +
                        "Varietal: %s\n" +
                        "Precio actual: $%.2f\n" +
                        "Fecha de actualización: %s",
                nombreVino, aniada, nombreBodega, nombreVarietal, precio, fecha);
        for (String destinatario : destinatarios) {
            enviarNotificacionPush(mensaje, destinatario);
        }
    }

    public void enviarNotificacionPush(String mensaje, String destinatario) {
        // Logica Notificacion Push
        System.out.println("Enviando notificación push a " + destinatario + ":");
        System.out.println(mensaje);
    }
}

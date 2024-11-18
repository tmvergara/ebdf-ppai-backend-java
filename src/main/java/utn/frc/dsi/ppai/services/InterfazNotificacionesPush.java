package utn.frc.dsi.ppai.services;

import java.util.Date;
import java.util.List;

public class InterfazNotificacionesPush implements ObservadorNotificacionesPush {

    // Códigos ANSI para estilizar texto en consola.
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BOLD = "\u001B[1m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_RED = "\u001B[31m";

    @Override
    public void actualizar(String nombreVino, Integer aniada, Double precio, String nombreBodega, String nombreVarietal, Date fecha, List<String> destinatarios) {
        String mensaje = String.format(
                ANSI_BOLD + ANSI_YELLOW +
                        "Actualización de vino: " + ANSI_RESET + "%s\n" +
                        ANSI_BOLD + "Añada: " + ANSI_RESET + "%d\n" +
                        ANSI_BOLD + "Bodega: " + ANSI_RESET + "%s\n" +
                        ANSI_BOLD + "Varietal: " + ANSI_RESET + "%s\n" +
                        ANSI_BOLD + "Precio actual: " + ANSI_GREEN + "$%.2f" + ANSI_RESET + "\n" +
                        ANSI_BOLD + "Fecha de actualización: " + ANSI_RESET + "%s\n",
                nombreVino, aniada, nombreBodega, nombreVarietal, precio, fecha);

        System.out.println(ANSI_BLUE + "================ NOTIFICACIÓN PUSH =================" + ANSI_RESET);
        for (String destinatario : destinatarios) {
            enviarNotificacionPush(mensaje, destinatario);
        }
        System.out.println(ANSI_BLUE + "===================================================" + ANSI_RESET);
    }

    public void enviarNotificacionPush(String mensaje, String destinatario) {
        System.out.println(ANSI_BOLD + ANSI_RED + "Destinatario: " + ANSI_RESET + destinatario);
        System.out.println(mensaje);
    }
}

package utn.frc.dsi.ppai.services;

import java.util.List;

public interface SujetoNotificador {
    void suscribir(List<ObservadorNotificacionesPush> obs);
    void quitar(List<ObservadorNotificacionesPush> obs);
    void notificar();
}

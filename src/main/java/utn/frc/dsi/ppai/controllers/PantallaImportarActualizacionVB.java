package utn.frc.dsi.ppai.controllers;

import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utn.frc.dsi.ppai.dtos.ErrorResponse;

@RestController
@RequestMapping("/actualizar-vinos-bodega")
public class PantallaImportarActualizacionVB {

    @GetMapping("/bodegas-con-actualizacion")
    public ResponseEntity<?> opcionImportarActualizacionVentana(){
        try {
            //PruebaDto found = service.findById(id);
            //return ResponseEntity.ok(found);
        } catch (ServiceException e) {
            ErrorResponse errorResponse = new ErrorResponse(
                    HttpStatus.NOT_FOUND.value(),
                    "Not Found",
                    e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @PostMapping("/actualizar-bodegas")
    public ResponseEntity<?> tomarBodegasSeleccionadas() {
        try {
            // PruebaDto found = service.findById(id);
            // return ResponseEntity.ok(found);
        } catch (ServiceException e) {
            ErrorResponse errorResponse = new ErrorResponse(
                    HttpStatus.NOT_FOUND.value(),
                    "Not Found",
                    e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
}

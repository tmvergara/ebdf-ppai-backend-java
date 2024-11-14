package utn.frc.dsi.ppai.controllers;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.frc.dsi.ppai.dtos.ErrorResponse;
import utn.frc.dsi.ppai.dtos.responses.ResumenActualizacionDto;
import utn.frc.dsi.ppai.services.GestorImportarActualizacionVB;

import java.util.List;

@RestController
@RequestMapping("/actualizar-vinos-bodega")
public class PantallaImportarActualizacionVB {
    private final GestorImportarActualizacionVB gestorImportarActualizacionVB;

    @Autowired
    public PantallaImportarActualizacionVB(GestorImportarActualizacionVB gestorImportarActualizacionVB){
        this.gestorImportarActualizacionVB = gestorImportarActualizacionVB;
    }

    @GetMapping("/bodegas-con-actualizacion")
    public ResponseEntity<?> opcionImportarActualizacionVentana(){
        try {
            //PruebaDto found = service.findById(id);
            return ResponseEntity.status(HttpStatus.OK).body("OK");
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
    public ResponseEntity<?> tomarBodegasSeleccionadas(@RequestBody List<String> bodegasSeleccionadas) {
        try {
            ResumenActualizacionDto resumenActualizacion = gestorImportarActualizacionVB.tomarBodegasSeleccionadas(bodegasSeleccionadas);
            return ResponseEntity.ok(resumenActualizacion);
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

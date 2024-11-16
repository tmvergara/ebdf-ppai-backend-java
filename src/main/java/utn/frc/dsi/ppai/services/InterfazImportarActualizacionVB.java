package utn.frc.dsi.ppai.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.frc.dsi.ppai.dtos.BodegaDto;
import utn.frc.dsi.ppai.dtos.VinoDto;
import utn.frc.dsi.ppai.dtos.responses.ActualizacionBodegaDto;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class InterfazImportarActualizacionVB {
    private final ObjectMapper objectMapper;

    @Autowired
    public InterfazImportarActualizacionVB(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    // Esta funcion simula la llamada a la API externa de las bodegas para traer las actualizaciones.
    public BodegaDto solicitarActualizacionAPI(String nombreBodega) throws ServiceException {
        String rutaArchivo = "src/main/resources/actualizacionBodegas.json";
        try {
            // Leer el JSON desde el archivo
            ActualizacionBodegaDto bodegasResponse = objectMapper.readValue(new File(rutaArchivo), ActualizacionBodegaDto.class);
            if (bodegasResponse.getBodegas() == null) {
                throw new IllegalStateException("El mapa de bodegas es nulo. Verifique la deserialización del JSON.");
            }

            // Buscar la lista de vinos para la bodega solicitada
            List<VinoDto> vinos = bodegasResponse.getBodegas().get(nombreBodega);
            System.out.println("Se encontro la bodega.");
            System.out.println(bodegasResponse);
            if (vinos != null) {
                // Crear y devolver la BodegaDto
                BodegaDto bodegaDto = new BodegaDto();
                bodegaDto.setNombre(nombreBodega);
                bodegaDto.setVinos(vinos);
                return bodegaDto;
            } else {
                return null; // Si no encuentra la bodega, devolver null
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("El servidor de Bodega externo no esta disponible en este momento.");
        }
    }

}

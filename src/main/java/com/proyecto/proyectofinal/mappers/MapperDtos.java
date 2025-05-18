package com.proyecto.proyectofinal.mappers;
import org.springframework.stereotype.Component;
import com.proyecto.proyectofinal.model.dtos.requestDtos.RequestActividadDTO;
import com.proyecto.proyectofinal.model.dtos.responseDtos.ResponseActividadDTO;

@Component
public class MapperDtos {
   
    public RequestActividadDTO responseToRequestActividadDTO(ResponseActividadDTO response) {
        RequestActividadDTO request = new RequestActividadDTO();
        request.setCedulaCreador(response.getCedulaCreador());
        request.setNombre(response.getNombreActividad());
        request.setDescripcion(response.getDescripcionActividad());
        request.setFechaInicio(response.getFechaInicio());
        request.setCapacidad(response.getCupos());
        request.setIntereses(response.getIntereses());
        request.setCiudad(response.getCiudad());
        request.setDireccion(response.getDireccion());
        request.setFechaCreacion(response.getFechaCreacion());

        return request;
    }

    
    

}

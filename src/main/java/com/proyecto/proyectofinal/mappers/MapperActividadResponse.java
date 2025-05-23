package com.proyecto.proyectofinal.mappers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.proyecto.proyectofinal.model.dtos.responseDtos.ResponseActividadDTO;
import com.proyecto.proyectofinal.model.entities.ActividadEntity;

@Component
public class MapperActividadResponse {
     
    @Autowired
    private MapperComentarioResponse mapperComentarioResponse;
    public ResponseActividadDTO requestToResponse(ActividadEntity actividad) {
        ResponseActividadDTO actividadDTO = ResponseActividadDTO.builder()
                .creador(actividad.getCreador().getNombreUsuario())
                .correoCreador(actividad.getCreador().getEmails().get(0).getEmail())
                .nombreActividad(actividad.getNombreActividad())
                .descripcionActividad(actividad.getDescripcionActividad())
                .direccionImagen(actividad.getImagenReferencia())
                .cupos(actividad.getCapacidad())
                .direccionFotoCreador(actividad.getCreador().getFotoPerfilReferencia())
                .cedulaCreador(actividad.getCreador().getCedula())
                .intereses(actividad.getInteresesActividad().stream().map(interes -> interes.getInteres()).toList())
                .fechaInicio(actividad.getFechaInicio())
                .fechaCreacion(actividad.getFechaCreacion())
                .direccion(actividad.getDireccionActividad().getId().getDireccion())
                .ciudad(actividad.getDireccionActividad().getId().getCiudad())
                .comentarios(mapperComentarioResponse.requestsToResponses(actividad.getComentarios()))
                .build();
        return actividadDTO;
    }

    public List<ResponseActividadDTO> requestsToResponses(List<ActividadEntity> actividades) {
        return actividades.stream().map(this::requestToResponse).toList();
    }
}

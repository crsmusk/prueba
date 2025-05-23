package com.proyecto.proyectofinal.mappers;

import java.util.List;

import org.springframework.stereotype.Component;

import com.proyecto.proyectofinal.model.dtos.responseDtos.ResponseComentarioDTO;
import com.proyecto.proyectofinal.model.dtos.responseDtos.ResponseUsuarioDTO;
import com.proyecto.proyectofinal.model.entities.ComentarioEntity;
import com.proyecto.proyectofinal.model.entities.UsuarioEntity;

@Component
public class MapperComentarioResponse {
    public ResponseComentarioDTO requestToResponse(ComentarioEntity comentario) {
        ResponseComentarioDTO comentarioDto = ResponseComentarioDTO.builder()
                .nickName(comentario.getUsuario().getNickName())
                .cedulaUsuario(comentario.getUsuario().getCedula())
                .nombreUsuario(comentario.getUsuario().getNombreUsuario())
                .contenidoComentario(comentario.getContenido())
                .fechaComentarioTexto(comentario.getFechaComentario().toString())
                .build();
        return comentarioDto;
    }

    public List<ResponseComentarioDTO> requestsToResponses(List<ComentarioEntity> comentario){
        return comentario.stream().map(this::requestToResponse).toList();
    }

}

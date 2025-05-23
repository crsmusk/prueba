package com.proyecto.proyectofinal.service.interfaces;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.proyecto.proyectofinal.model.dtos.requestDtos.RequestComentariosDTO;
import com.proyecto.proyectofinal.model.dtos.responseDtos.ResponseComentarioDTO;
import com.proyecto.proyectofinal.model.entities.ComentarioEntity;

public interface ComentarioService {
    ComentarioEntity guardarComentario(RequestComentariosDTO comentario);
    void eliminarComentario(LocalDateTime id, String nickName);
    List<ResponseComentarioDTO> buscarComentariosPorActividadId(LocalDateTime fechaCreacion);
}
package com.proyecto.proyectofinal.service.interfaces;

import java.util.Optional;

import com.proyecto.proyectofinal.model.entities.ComentarioEntity;

public interface ComentarioService {
    ComentarioEntity guardarComentario(ComentarioEntity comentario);
    Optional<ComentarioEntity> buscarPorId(Long id);
    Long contarComentariosPorActividad(Long actividadId);
    void eliminarComentario(Long id);
}
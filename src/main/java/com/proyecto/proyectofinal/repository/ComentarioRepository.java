package com.proyecto.proyectofinal.repository;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.proyectofinal.model.entities.ComentarioEntity;

@Repository
public interface ComentarioRepository extends JpaRepository<ComentarioEntity,LocalDateTime> {
    
    // Contar comentarios por actividad
    @Query("SELECT COUNT(c) FROM ComentarioEntity c WHERE c.actividad.fechaCreacion = :fechaCreacion")
    Long numeroDeComentarios(@Param("fechaCreacion") LocalDateTime fechaCreacion);

    // Borrar comentario por fechaComentario
    @Modifying
    @Transactional
    @Query("DELETE FROM ComentarioEntity c WHERE c.fechaComentario = :fechaComentario")
    void borrarComentario(@Param("fechaComentario") LocalDateTime fechaComentario);

    // Buscar comentarios por fechaCreacion de actividad
    @Query("SELECT c FROM ComentarioEntity c WHERE c.actividad.fechaCreacion = :fechaCreacion")
    List<ComentarioEntity> buscarPorActividadId(@Param("fechaCreacion") LocalDateTime fechaCreacion);

    // Verificar si un comentario pertenece a un usuario por nickName
    @Query("SELECT COUNT(c) > 0 FROM ComentarioEntity c WHERE c.fechaComentario = :fechaComentario AND c.usuario.nickName = :nickName")
    boolean comentarioPerteneceAUsuario(@Param("fechaComentario") LocalDateTime fechaComentario, @Param("nickName") String nickName);
}
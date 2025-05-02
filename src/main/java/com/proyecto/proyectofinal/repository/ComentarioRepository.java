package com.proyecto.proyectofinal.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyecto.proyectofinal.model.entities.ComentarioEntity;

@Repository
public interface ComentarioRepository extends JpaRepository<ComentarioEntity, Long> {
    
    // Contar comentarios por actividad
    @Query("SELECT COUNT(c) FROM ComentarioEntity c WHERE c.actividad.id = :actividadId")
    Long numeroDeComentarios(@Param("actividadId") Long actividadId);

    // Borrar comentario 
    @Query("DELETE FROM ComentarioEntity c WHERE c.id = :id")
    void borrarComentario(@Param("id") Long id);
}
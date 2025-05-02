package com.proyecto.proyectofinal.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyecto.proyectofinal.model.entities.NotificacionEntity;

@Repository
public interface NotificacionRepository extends JpaRepository<NotificacionEntity, Long> {
     
    // Eliminar notificacion
    @Query("DELETE FROM NotificacionEntity n WHERE n.idNotificacion = :id")
    void borrarActividad(@Param("id") Long id);
    
}
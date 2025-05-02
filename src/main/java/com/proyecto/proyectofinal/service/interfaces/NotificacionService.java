package com.proyecto.proyectofinal.service.interfaces;

import java.util.Optional;
import java.util.List;

import com.proyecto.proyectofinal.model.entities.NotificacionEntity;

public interface NotificacionService {
    NotificacionEntity guardarNotificacion(NotificacionEntity notificacion);
    Optional<NotificacionEntity> buscarPorId(Long id);
    List<NotificacionEntity> listarTodas();
    void eliminarNotificacion(Long id);
}
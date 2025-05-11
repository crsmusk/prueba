package com.proyecto.proyectofinal.service.interfaces;

import java.util.Optional;
import java.util.List;

import com.proyecto.proyectofinal.model.entities.NotificacionEntity;
import com.proyecto.proyectofinal.model.entities.UsuarioEntity;

public interface NotificacionService {
    NotificacionEntity guardarNotificacion(List<UsuarioEntity> usuarios, String mensaje,String actividad);
}
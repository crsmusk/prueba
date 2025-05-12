package com.proyecto.proyectofinal.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.proyectofinal.model.entities.NotificacionEntity;
import com.proyecto.proyectofinal.model.entities.UsuarioEntity;
import com.proyecto.proyectofinal.repository.NotificacionRepository;
import com.proyecto.proyectofinal.service.interfaces.NotificacionService;

public class NotificionServiceImpl implements NotificacionService {
 
    @Autowired
    private NotificacionRepository notificacionRepository;
     
    @Transactional
    @Override
    public NotificacionEntity guardarNotificacion(List<UsuarioEntity> usuarios, String mensaje,String actividad) {
        NotificacionEntity notificacionGuardada = new NotificacionEntity();
        notificacionGuardada.setContenido(mensaje);
        notificacionGuardada.setActividad(actividad);
        notificacionGuardada.setDestinatarios(usuarios);
        
        return this.notificacionRepository.save(notificacionGuardada);
    }

}

package com.proyecto.proyectofinal.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.proyecto.proyectofinal.model.entities.EmailEntity;

public interface EmailService {
    EmailEntity guardarEmail(EmailEntity email);
    void eliminarEmail(String email);
  
}
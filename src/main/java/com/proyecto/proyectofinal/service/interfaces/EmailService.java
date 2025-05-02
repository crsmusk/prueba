package com.proyecto.proyectofinal.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.proyecto.proyectofinal.model.entities.EmailEntity;

public interface EmailService {
    EmailEntity guardarEmail(EmailEntity email);
    Optional<EmailEntity> buscarPorEmail(String email);
    List<EmailEntity> listarTodos();
    void eliminarEmail(String email);
    boolean existePorEmail(String email);
}
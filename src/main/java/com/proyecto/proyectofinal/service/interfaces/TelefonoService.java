package com.proyecto.proyectofinal.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.proyecto.proyectofinal.model.entities.TelefonoEntity;

public interface TelefonoService {
    TelefonoEntity guardarTelefono(TelefonoEntity telefono);
    Optional<TelefonoEntity> buscarPorNumero(String numeroTelefono);
    List<TelefonoEntity> listarTodos();
    void eliminarTelefono(String numeroTelefono);
    boolean existePorNumero(String numeroTelefono);
}
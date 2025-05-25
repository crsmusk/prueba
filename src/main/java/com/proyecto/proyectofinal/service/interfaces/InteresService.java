package com.proyecto.proyectofinal.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.proyecto.proyectofinal.model.entities.InteresEntity;

public interface InteresService {
    InteresEntity guardarInteres(InteresEntity interes);
    Optional<InteresEntity> buscarPorNombre(String nombre);
    List<InteresEntity> listarTodos();
    
    void eliminarInteres(String nombre);
}
package com.proyecto.proyectofinal.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.proyecto.proyectofinal.model.entities.RolEntity;

public interface RolService {
    RolEntity guardarRol(RolEntity rol);
    Optional<RolEntity> buscarPorNombre(String nombreRol);
    List<RolEntity> listarTodos();
    void eliminarRol(String nombreRol);
}
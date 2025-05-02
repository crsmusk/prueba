package com.proyecto.proyectofinal.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.proyecto.proyectofinal.model.entities.UsuarioEntity;

public interface UsuarioService {
    UsuarioEntity guardarUsuario(UsuarioEntity usuario);
    Optional<UsuarioEntity> buscarPorCedula(String cedula);
    Optional<UsuarioEntity> buscarPorNombreUsuario(String nombreUsuario);
    List<UsuarioEntity> buscarPorInteres(String interes);
    List<UsuarioEntity> listarTodos();
    void eliminarUsuario(String cedula);
    //boolean existePorCedula(String cedula);
}
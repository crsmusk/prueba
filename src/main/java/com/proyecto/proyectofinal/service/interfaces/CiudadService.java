package com.proyecto.proyectofinal.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.proyecto.proyectofinal.model.entities.CiudadEntity;

public interface CiudadService {
    CiudadEntity guardarCiudad(String ciudad);
    Optional<CiudadEntity> buscarPorNombre(String nombreCiudad);
    List<CiudadEntity> listarTodas();
    List<CiudadEntity> obtenerCiudadesConActividades();
    void eliminarCiudad(String nombreCiudad);
}
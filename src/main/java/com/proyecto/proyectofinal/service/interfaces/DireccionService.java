package com.proyecto.proyectofinal.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.proyecto.proyectofinal.model.entities.DireccionEntity;
import com.proyecto.proyectofinal.model.idsEmbedded.IdDireccion;

public interface DireccionService {
    DireccionEntity guardarDireccion(DireccionEntity direccion);
    Optional<DireccionEntity> buscarPorId(IdDireccion id);
    List<DireccionEntity> buscarPorTexto(String texto);
    List<DireccionEntity> listarTodas();
    void eliminarDireccion(IdDireccion id);
}
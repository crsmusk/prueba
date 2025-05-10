package com.proyecto.proyectofinal.service.interfaces;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.proyecto.proyectofinal.model.entities.ActividadEntity;
import com.proyecto.proyectofinal.model.idsEmbedded.IdActividad;

public interface ActividadService {
    ActividadEntity guardarActividad(ActividadEntity actividad);
    Optional<ActividadEntity> buscarPorId(IdActividad id);
    List<ActividadEntity> buscarPorCiudad(String ciudad);
    List<ActividadEntity> buscarPorInteres(String interes);
    List<ActividadEntity> buscarPorCreador(String cedula);
    List<ActividadEntity> buscarEntreFechas(LocalDateTime inicio, LocalDateTime fin);
    List<ActividadEntity> buscarProximasActividades();
    void eliminarActividad(IdActividad id);
}
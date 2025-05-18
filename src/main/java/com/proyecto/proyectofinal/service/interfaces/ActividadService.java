package com.proyecto.proyectofinal.service.interfaces;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.proyecto.proyectofinal.model.dtos.requestDtos.RequestActividadDTO;
import com.proyecto.proyectofinal.model.dtos.responseDtos.ResponseActividadDTO;
import com.proyecto.proyectofinal.model.entities.ActividadEntity;

public interface ActividadService {
    ActividadEntity guardarActividad(RequestActividadDTO actividadDto);
    ResponseActividadDTO buscarPorId(LocalDateTime fechaCreacion);
    List<ResponseActividadDTO> buscarPorCiudad(String ciudad);
    List<ResponseActividadDTO> buscarPorInteres(String interes);
    List<ResponseActividadDTO> buscarPorCreador(String cedula);
    List<ResponseActividadDTO> buscarEntreFechas(LocalDateTime inicio);
    List<ResponseActividadDTO> buscarProximasActividades();
    List<ResponseActividadDTO> buscarPorNombreActividad(String nombre);
    List<ResponseActividadDTO> buscarPorParticipante(String cedula);
    void eliminarActividad(LocalDateTime fechaCreacion);
    void actualizarActividad(LocalDateTime fechaCreacion, RequestActividadDTO actividadDto)throws IOException;
    ActividadEntity inscribirUsuario(LocalDateTime fechaCreacion, String cedulaUsuario);
}
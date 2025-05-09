package com.proyecto.proyectofinal.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.proyectofinal.model.entities.ActividadEntity;
import com.proyecto.proyectofinal.model.idsEmbedded.IdActividad;
import com.proyecto.proyectofinal.repository.ActividadRepository;
import com.proyecto.proyectofinal.service.interfaces.ActividadService;



@Service
public class ActividadServiceImpl implements ActividadService {

    @Autowired
    private ActividadRepository actividadRepository;
    
    @Transactional
    @Override
    public ActividadEntity guardarActividad(ActividadEntity actividad) {
        ActividadEntity actividadGuardada = this.actividadRepository.findById(actividad.getId())
            .orElse( actividadRepository.save(actividad));
        return actividadGuardada;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<ActividadEntity> buscarPorId(IdActividad id) {
        return actividadRepository.findById(id);
    }
     
    @Transactional(readOnly = true)
    @Override
    public List<ActividadEntity> buscarPorCiudad(String ciudad) {
        return actividadRepository.buscarActividadPorCiudad(ciudad);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ActividadEntity> buscarPorInteres(String interes) {
        return actividadRepository.buscarActividadesPorInteres(interes);
    }
    @Transactional(readOnly = true)
    @Override
    public List<ActividadEntity> buscarPorCreador(String cedula) {
        return actividadRepository.buscarActividadesPorCreador(cedula);
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<ActividadEntity> buscarEntreFechas(LocalDateTime inicio, LocalDateTime fin) {
        return actividadRepository.findActivitiesBetweenDates(inicio, fin);
    }
 
    @Transactional(readOnly = true)
    @Override
    public List<ActividadEntity> buscarProximasActividades() {
        return actividadRepository.buscarProximasActividades();
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<ActividadEntity> listarTodas() {
        return actividadRepository.findAll();
    }

    @Override
    public void eliminarActividad(IdActividad id) {
        actividadRepository.deleteById(id);
    }
}
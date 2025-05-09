package com.proyecto.proyectofinal.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.proyectofinal.model.entities.CiudadEntity;
import com.proyecto.proyectofinal.repository.CiudadRepository;
import com.proyecto.proyectofinal.service.interfaces.CiudadService;

@Service
public class CiudadServiceImpl implements CiudadService {

    @Autowired
    private CiudadRepository ciudadRepository;

    @Transactional
    @Override
    public CiudadEntity guardarCiudad(CiudadEntity ciudad) {
        CiudadEntity ciudadGuardada = ciudadRepository
        .findById(ciudad.getNombreCiudad())
        .orElse(this.ciudadRepository.save(ciudad));
        return ciudadGuardada;
    }
    
    @Transactional(readOnly = true)
    @Override
    public Optional<CiudadEntity> buscarPorNombre(String nombreCiudad) {
        return ciudadRepository.findById(nombreCiudad);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CiudadEntity> listarTodas() {
        return ciudadRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<CiudadEntity> obtenerCiudadesConActividades() {
        return ciudadRepository.ciudadesConActividades();
    }

 
    @Override
    public void eliminarCiudad(String nombreCiudad) {
        
        if(this.ciudadRepository.existsById(nombreCiudad)){
            ciudadRepository.deleteById(nombreCiudad);
        }
       
    }
}
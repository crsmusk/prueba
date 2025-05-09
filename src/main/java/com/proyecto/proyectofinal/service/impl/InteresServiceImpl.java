package com.proyecto.proyectofinal.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.proyecto.proyectofinal.model.entities.InteresEntity;
import com.proyecto.proyectofinal.service.interfaces.InteresService;

@Service
public class InteresServiceImpl implements InteresService {

    @Override
    public InteresEntity guardarInteres(InteresEntity interes) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'guardarInteres'");
    }

    @Override
    public Optional<InteresEntity> buscarPorNombre(String nombre) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorNombre'");
    }

    @Override
    public List<InteresEntity> listarTodos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarTodos'");
    }

    @Override
    public List<InteresEntity> obtenerInteresesPopulares() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerInteresesPopulares'");
    }

    @Override
    public void eliminarInteres(String nombre) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarInteres'");
    }
    
   

}

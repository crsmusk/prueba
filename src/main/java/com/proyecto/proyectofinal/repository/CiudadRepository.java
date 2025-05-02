package com.proyecto.proyectofinal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proyecto.proyectofinal.model.entities.CiudadEntity;

@Repository
public interface CiudadRepository extends JpaRepository<CiudadEntity, String> {
    
    
    // Buscar ciudades con actividades
    @Query("SELECT DISTINCT c FROM CiudadEntity c JOIN DireccionEntity d ON d.ciudad = c JOIN ActividadEntity a ON a.direccionActividad = d")
    List<CiudadEntity> ciudadesConActividades();
    
}
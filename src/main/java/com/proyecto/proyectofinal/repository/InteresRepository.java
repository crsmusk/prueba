package com.proyecto.proyectofinal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proyecto.proyectofinal.model.entities.InteresEntity;

@Repository
public interface InteresRepository extends JpaRepository<InteresEntity, String> {
    
    // Encontrar los intereses más populares 
    @Query("SELECT i, COUNT(u) as usuariosCount FROM InteresEntity i LEFT JOIN i.usuarios u GROUP BY i ORDER BY usuariosCount DESC")
    List<InteresEntity> interesesMasPopulares(); 
    
    // Buscar intereses por nombre
    InteresEntity findByInteresIgnoreCase(String nombre);
    
}
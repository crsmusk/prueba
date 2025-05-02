package com.proyecto.proyectofinal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyecto.proyectofinal.model.entities.DireccionEntity;
import com.proyecto.proyectofinal.model.idsEmbedded.IdDireccion;

@Repository
public interface DireccionRepository extends JpaRepository<DireccionEntity, IdDireccion> {
    
    // Buscar direcciones que contengan cierto texto
    @Query("SELECT d FROM DireccionEntity d WHERE LOWER(d.id.direccion) LIKE LOWER(CONCAT('%', :texto, '%'))")
    List<DireccionEntity> buscarDireccion(@Param("texto") String texto);
}
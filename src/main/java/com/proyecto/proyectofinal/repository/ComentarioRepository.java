package com.proyecto.proyectofinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.proyectofinal.model.entities.ComentarioEntity;

@Repository
public interface ComentarioRepository extends JpaRepository<ComentarioEntity, Long> {
}
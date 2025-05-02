package com.proyecto.proyectofinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.proyectofinal.model.entities.DireccionEntity;
import com.proyecto.proyectofinal.model.idsEmbedded.IdDireccion;

@Repository
public interface DireccionRepository extends JpaRepository<DireccionEntity, IdDireccion> {
}
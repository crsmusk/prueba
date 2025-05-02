package com.proyecto.proyectofinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.proyectofinal.model.entities.InteresEntity;

@Repository
public interface InteresRepository extends JpaRepository<InteresEntity, String> {
}
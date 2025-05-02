package com.proyecto.proyectofinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.proyectofinal.model.entities.RolEntity;

@Repository
public interface RolRepository extends JpaRepository<RolEntity, String> {
}
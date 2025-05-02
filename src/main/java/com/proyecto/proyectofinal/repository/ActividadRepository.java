package com.proyecto.proyectofinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.proyectofinal.model.entities.ActividadEntity;
import com.proyecto.proyectofinal.model.idsEmbedded.IdActividad;

@Repository
public interface ActividadRepository extends JpaRepository<ActividadEntity, IdActividad> {
}
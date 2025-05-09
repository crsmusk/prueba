package com.proyecto.proyectofinal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyecto.proyectofinal.model.entities.TelefonoEntity;
import com.proyecto.proyectofinal.model.idsEmbedded.IdTelefono;

@Repository
public interface TelefonoRepository extends JpaRepository<TelefonoEntity, IdTelefono> {
    
    @Query("SELECT t FROM TelefonoEntity t WHERE t.id.numeroTelefono = :numeroTelefono")
    Optional<TelefonoEntity> findByNumeroTelefono(@Param("numeroTelefono") String numeroTelefono);
    
    @Query("SELECT t FROM TelefonoEntity t WHERE t.usuario.cedula = :cedulaUsuario")
    List<TelefonoEntity> findByUsuarioCedula(@Param("cedulaUsuario") String cedulaUsuario);
}
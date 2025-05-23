package com.proyecto.proyectofinal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyecto.proyectofinal.model.entities.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, String> {
    
    // Buscar usuario por nombre de usuario
    Optional<UsuarioEntity> findByNombreUsuario(String nombreUsuario);
    
    // Buscar usuarios por interes
    @Query("SELECT u FROM UsuarioEntity u JOIN u.intereses i WHERE i.interes = :interes")
    List<UsuarioEntity> buscarPorInteres(@Param("interes") String interes);

    //buscar por nickName
    Optional<UsuarioEntity> findByNickNameIgnoreCase(String nickName);
          
}
package com.proyecto.proyectofinal.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.proyectofinal.model.entities.EmailEntity;

@Repository
public interface EmailRepository extends JpaRepository<EmailEntity, String> {
    Optional<EmailEntity> findByEmail(String email);
}

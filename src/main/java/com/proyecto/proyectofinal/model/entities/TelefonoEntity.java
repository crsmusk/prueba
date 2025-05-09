package com.proyecto.proyectofinal.model.entities;

import com.proyecto.proyectofinal.model.idsEmbedded.IdTelefono;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "telefonos")
public class TelefonoEntity {
    
    @EmbeddedId
    private IdTelefono id;
    
    @MapsId("usuarioCedula")
    @ManyToOne
    @JoinColumn(name = "usuario_cedula")
    private UsuarioEntity usuario;
}

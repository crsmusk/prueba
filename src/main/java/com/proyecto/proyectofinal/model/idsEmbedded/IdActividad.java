package com.proyecto.proyectofinal.model.idsEmbedded;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class IdActividad implements Serializable {
    
    @Column(name = "fecha_inicio")
    private LocalDateTime fechaInicio;
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
}

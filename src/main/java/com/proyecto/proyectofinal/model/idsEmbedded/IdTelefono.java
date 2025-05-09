package com.proyecto.proyectofinal.model.idsEmbedded;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class IdTelefono implements Serializable {
    
    @Size(max = 15)
    private String numeroTelefono;
    private String usuarioCedula;
}
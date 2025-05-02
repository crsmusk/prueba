package com.proyecto.proyectofinal.model.idEmbebido;

import java.io.Serializable;


import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
@Embeddable
public class IdDireccion implements Serializable {
  
    @Size(max = 80)
    private String direccion;
    private String ciudad;
}

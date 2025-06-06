package com.proyecto.proyectofinal.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "emails")
public class EmailEntity {
    @Id
    @Size(max = 60)
    @Column( unique = true)
    private String email;
    
    @ManyToOne
    @JoinColumn(name = "usuario_cedula")
    private UsuarioEntity usuario;
}

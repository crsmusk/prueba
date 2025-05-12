package com.proyecto.proyectofinal.model.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "comentarios")
public class ComentarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Size(max = 500)
    private String contenido;
    
    @ManyToOne
    @JoinColumn(name = "usuario_cedula")
    private UsuarioEntity usuario;
    
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "fecha_inicio", referencedColumnName = "fecha_inicio"),
        @JoinColumn(name = "fecha_creacion", referencedColumnName = "fecha_creacion")
    })
    private ActividadEntity actividad;
}

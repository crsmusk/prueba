package com.proyecto.proyectofinal.model.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@Entity
@Table(name = "actividades")
public class ActividadEntity {
    @Id
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
      
    @Column(name = "fecha_inicio")
    private LocalDateTime fechaInicio;
    
    @Column(name = "nombre_actividad")
    @Size(max = 50)
    private String nombreActividad;
    
    @Column(name = "descripcion_actividad")
    @Size(max = 500)
    private String descripcionActividad;
   
    private int capacidad;
    @Column(name="imagen_referencia")
    private String imagenReferencia;
     
    @ManyToOne
    @JoinColumn(name = "creador_cedula")
    private UsuarioEntity creador;    @ManyToMany
    @JoinTable(name = "participantes",
            joinColumns = @JoinColumn(name = "fecha_creacion", referencedColumnName = "fecha_creacion"),
            inverseJoinColumns = @JoinColumn(name = "usuario_cedula"))
    private List<UsuarioEntity> participantes;

    @ManyToMany
    @JoinTable(name = "intereses_actividad",
            joinColumns = @JoinColumn(name = "fecha_creacion", referencedColumnName = "fecha_creacion"),
            inverseJoinColumns = @JoinColumn(name = "interes"))
    private List<InteresEntity> interesesActividad;

    @OneToMany(mappedBy = "actividad", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<ComentarioEntity> comentarios;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "direccion", referencedColumnName = "direccion"),
        @JoinColumn(name = "ciudad", referencedColumnName = "ciudad_id")
    })
    private DireccionEntity direccionActividad;
}

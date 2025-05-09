package com.proyecto.proyectofinal.model.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
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

import java.util.List;

import com.proyecto.proyectofinal.model.idsEmbedded.IdActividad;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@Entity
@Table(name = "actividades")
public class ActividadEntity {

    @EmbeddedId
    private IdActividad id;
    
    @Column(name = "nombre_actividad")
    @Size(max = 50)
    private String nombreActividad;
    
    @Column(name = "descripcion_actividad")
    @Size(max = 500)
    private String descripcionActividad;
     
    @ManyToOne
    @JoinColumn(name = "creador_cedula")
    private UsuarioEntity creador;

    @ManyToMany
    @JoinTable(name = "participantes",
            joinColumns = {
                @JoinColumn(name = "fecha_inicio", referencedColumnName = "fecha_inicio"),
                @JoinColumn(name = "fecha_fin", referencedColumnName = "fecha_fin")
            },
            inverseJoinColumns = @JoinColumn(name = "usuario_cedula"))
    private List<UsuarioEntity> participantes;

    @ManyToMany
    @JoinTable(name = "intereses_actividad",
            joinColumns = {
                @JoinColumn(name = "fecha_inicio", referencedColumnName = "fecha_inicio"),
                @JoinColumn(name = "fecha_fin", referencedColumnName = "fecha_fin")
            },
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

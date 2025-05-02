package com.proyecto.proyectofinal.model.entities;



import com.proyecto.proyectofinal.model.idsEmbedded.IdDireccion;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "direcciones")
public class DireccionEntity {
    @EmbeddedId
    IdDireccion id;

    @MapsId("ciudad")
    @ManyToOne
    @JoinColumn(name = "ciudad_id")
    private CiudadEntity ciudad; 
}

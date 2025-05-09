package com.proyecto.proyectofinal.model.entities;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
@Table(name = "intereses")
public class InteresEntity {
    
    @Id
    @Size(max = 20)
    @Column(name = "interes", unique = true)
    private String interes;

    @ManyToMany(mappedBy = "intereses")
    private List<UsuarioEntity> usuarios;
}

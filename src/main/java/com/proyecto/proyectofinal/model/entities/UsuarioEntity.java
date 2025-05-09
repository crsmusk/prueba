package com.proyecto.proyectofinal.model.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Builder
@Table(name = "usuarios")
public class UsuarioEntity {

    @Id
    @Size(max = 20)
    private String cedula;
    
    @Column(name = "nombre_usuario")
    @Size(max = 20)
    private String nombreUsuario;
    
    @Column(name = "apellido_usuario")
    @Size(max = 20)
    private String apellidoUsuario;
    
    @Size(max = 15)
    private String contrasena;

    @Column(name = "foto_perfil_referencia")
    private String fotoPerfilReferencia;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TelefonoEntity> telefonos;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmailEntity> emails;
     
    @ManyToMany
    @JoinTable(name = "usuarios_intereses",
            joinColumns = @JoinColumn(name = "usuario_cedula"),
            inverseJoinColumns = @JoinColumn(name = "interes"))
    private List<InteresEntity> intereses;
     
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuarios_roles",
            joinColumns = @JoinColumn(name = "usuario_cedula"),
            inverseJoinColumns = @JoinColumn(name = "rol"))
    private List<RolEntity> roles;
    
    @ManyToMany
    @JoinTable(name = "usuarios_notificaciones",
            joinColumns = @JoinColumn(name = "usuario_cedula"),
            inverseJoinColumns = @JoinColumn(name = "notificacion_id"))
    private List<NotificacionEntity> notificacion;
}

package com.proyecto.proyectofinal.model.dtos.requestDtos;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestUsuarioDTO {
    
    @Size(max = 20)
    private String cedula;
    
    @Size(max = 20)
    private String nombreUsuario;
    
    @Size(max = 20)
    private String apellidoUsuario;
    
    @Size(max = 15)
    private String contrasena;
    
    private MultipartFile fotoPerfil;
    
    private List<String> telefonos;
    
    private List<String> emails;
    
    private List<String> intereses;
    
    private List<String> roles;

    private String nickName;
}
package com.proyecto.proyectofinal.mappers;

import java.util.List;

import org.springframework.stereotype.Component;

import com.proyecto.proyectofinal.model.dtos.responseDtos.ResponseUsuarioDTO;
import com.proyecto.proyectofinal.model.entities.UsuarioEntity;

@Component
public class MapperUsuarioResponse {


    public ResponseUsuarioDTO requestToResponse(UsuarioEntity usuario){
        ResponseUsuarioDTO usuarioDTO=ResponseUsuarioDTO.builder()
        .cedula(usuario.getCedula())
        .nombreUsuario(usuario.getNombreUsuario())
        .apellidoUsuario(usuario.getApellidoUsuario())
        .direccionPerfilImagen(usuario.getFotoPerfilReferencia())
        .telefonoUsuario(usuario.getTelefonos().stream().map(telefono->telefono.getId().getNumeroTelefono()).toList())
        .correoUsuario(usuario.getEmails().stream().map(email -> email.getEmail()).toList())
        .build();
        return usuarioDTO;
    }
    public List<ResponseUsuarioDTO> requestsToResponses(List<UsuarioEntity> usuarios){
        return usuarios.stream().map(this::requestToResponse).toList();
    }
}

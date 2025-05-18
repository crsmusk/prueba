package com.proyecto.proyectofinal.model.dtos.responseDtos;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResponseUsuarioDTO {
  private String direccionPerfilImagen;
  private String cedula;
  private String nombreUsuario;
  private String apellidoUsuario;
  private List<String> correoUsuario;
  private List<String> telefonoUsuario;
}

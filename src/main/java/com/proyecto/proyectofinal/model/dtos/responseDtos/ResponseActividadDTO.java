package com.proyecto.proyectofinal.model.dtos.responseDtos;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseActividadDTO {
   private String creador;
   private String cedulaCreador;
   private String correoCreador;
   private String nombreActividad;
   private LocalDateTime fechaInicio;
   private LocalDateTime fechaCreacion;
   private String direccion;
   private String descripcionActividad;
   private String direccionImagen;
   private String ciudad;
   private List<String> intereses;
   private int cupos;
   private String direccionFotoCreador;
}

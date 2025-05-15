package com.proyecto.proyectofinal.model.dtos.requestDtos;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class RequestActividadDTO {
    private String cedulaCreador;
    private String nombre;
    private String descripcion;
    private LocalDateTime fechaInicio;
    private String ubicacion;
    private int capacidad;
    private List<String> intereses;
    private String ciudad;
    private String direccion;
}

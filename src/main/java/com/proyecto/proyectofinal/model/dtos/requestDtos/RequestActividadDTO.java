package com.proyecto.proyectofinal.model.dtos.requestDtos;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
public class RequestActividadDTO {
    private String cedulaCreador;
    private String nombre;
    private String descripcion;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaCreacion;
    private String fechaCreacionTexto;
    private int capacidad;
    private List<String> intereses;
    private String ciudad;
    private String direccion;
    private MultipartFile fotoActividad;
    private String nicknameCreador;
}

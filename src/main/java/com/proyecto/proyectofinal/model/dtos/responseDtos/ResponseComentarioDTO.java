package com.proyecto.proyectofinal.model.dtos.responseDtos;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ResponseComentarioDTO {
    private String nickName;
    private String cedulaUsuario;
    private String nombreUsuario;
    private String contenidoComentario;
    private String fechaComentarioTexto;
} 

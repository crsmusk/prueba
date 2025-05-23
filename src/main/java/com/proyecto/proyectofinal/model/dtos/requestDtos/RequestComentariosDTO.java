package com.proyecto.proyectofinal.model.dtos.requestDtos;

import java.time.LocalDateTime;
import org.springframework.stereotype.Component;
import lombok.Data;

@Data
@Component
public class RequestComentariosDTO {
    private String contenidoComentario;
    private String nickName;
    private LocalDateTime actividadId;

}

package com.proyecto.proyectofinal.service.impl;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto.proyectofinal.model.dtos.responseDtos.ResponseComentarioDTO;
import com.proyecto.proyectofinal.mappers.MapperComentarioResponse;
import com.proyecto.proyectofinal.model.dtos.requestDtos.RequestComentariosDTO;
import com.proyecto.proyectofinal.model.entities.ComentarioEntity;
import com.proyecto.proyectofinal.repository.ActividadRepository;
import com.proyecto.proyectofinal.repository.ComentarioRepository;
import com.proyecto.proyectofinal.repository.UsuarioRepository;
import com.proyecto.proyectofinal.service.interfaces.ComentarioService;

@Service
public class ComentariosServiceImpl implements ComentarioService{

    @Autowired
    private ComentarioRepository comentarioRepository;
    @Autowired
    private ActividadRepository actividadRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private MapperComentarioResponse  mapper;

    @Override
    public ComentarioEntity guardarComentario(RequestComentariosDTO comentario) {
        ComentarioEntity comentarioEntity = new ComentarioEntity();
        comentarioEntity.setContenido(comentario.getContenidoComentario());
        comentarioEntity.setFechaComentario(LocalDateTime.now());
        comentarioEntity.setActividad(actividadRepository.findById(comentario.getActividadId()).orElse(null));
        comentarioEntity.setUsuario(this.usuarioRepository.findByNickNameIgnoreCase(comentario.getNickName()).orElse(null));

        this.comentarioRepository.save(comentarioEntity);

        return comentarioEntity;
    }

   

    @Override
    public void eliminarComentario(LocalDateTime id, String nickName) {
       
        if(this.comentarioRepository.comentarioPerteneceAUsuario(id, nickName)){
            this.comentarioRepository.deleteById(id);
        }
    }

    @Override
    public List<ResponseComentarioDTO> buscarComentariosPorActividadId(LocalDateTime fechaCreacion) {
      return mapper.requestsToResponses(this.comentarioRepository.buscarPorActividadId(fechaCreacion));
    }

}

package com.proyecto.proyectofinal.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.proyectofinal.model.dtos.requestDtos.RequestActividadDTO;
import com.proyecto.proyectofinal.model.entities.ActividadEntity;
import com.proyecto.proyectofinal.model.entities.EmailEntity;
import com.proyecto.proyectofinal.model.entities.InteresEntity;
import com.proyecto.proyectofinal.model.entities.UsuarioEntity;
import com.proyecto.proyectofinal.model.idsEmbedded.IdActividad;
import com.proyecto.proyectofinal.repository.ActividadRepository;
import com.proyecto.proyectofinal.service.interfaces.ActividadService;
import com.proyecto.proyectofinal.service.interfaces.UsuarioService;




@Service
public class ActividadServiceImpl implements ActividadService {

    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private ActividadRepository actividadRepository;
    
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private InteresServiceImpl interesService;

    @Autowired
    private DireccionServiceImpl direccionService;
    
    @Transactional
    @Override
    public ActividadEntity guardarActividad(RequestActividadDTO actividadDto) {
        IdActividad idActividad =new IdActividad();
        idActividad.setFechaInicio(actividadDto.getFechaInicio());
        idActividad.setFechaCreacion(LocalDateTime.now());
        
        ActividadEntity actividad = new ActividadEntity();
        actividad.setId(idActividad);
        actividad.setNombreActividad(actividadDto.getNombre());
        actividad.setDescripcionActividad(actividadDto.getDescripcion());
        actividad.setCapacidad(actividadDto.getCapacidad());
        actividad.setDireccionActividad(this.direccionService.guardarDireccion(actividadDto.getDireccion(),actividadDto.getCiudad()));
        actividad.setCreador(this.usuarioService.buscarPorCedula(actividadDto.getCedulaCreador()).orElse(null));
        actividad.setInteresesActividad(optenerIntereses(actividadDto.getIntereses()));
         
        return this.actividadRepository.save(actividad);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<ActividadEntity> buscarPorId(IdActividad id) {
        return actividadRepository.findById(id);
    }
     
    @Transactional(readOnly = true)
    @Override
    public List<ActividadEntity> buscarPorCiudad(String ciudad) {
        return actividadRepository.buscarActividadPorCiudad(ciudad);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ActividadEntity> buscarPorInteres(String interes) {
        return actividadRepository.buscarActividadesPorInteres(interes);
    }
    @Transactional(readOnly = true)
    @Override
    public List<ActividadEntity> buscarPorCreador(String cedula) {
        return actividadRepository.buscarActividadesPorCreador(cedula);
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<ActividadEntity> buscarEntreFechas(LocalDateTime inicio) {
        return actividadRepository.buscarPorFecha(inicio);
    }
 
    @Transactional(readOnly = true)
    @Override
    public List<ActividadEntity> buscarProximasActividades() {
        return actividadRepository.buscarProximasActividades();
    }
    

    @Override
    public void eliminarActividad(IdActividad id) {
        if (this.actividadRepository.existsById(id)) {
            ActividadEntity actividad = this.actividadRepository.findById(id).orElse(null);
            for (UsuarioEntity participante : actividad.getParticipantes()) {
                emailService.recordatorioActividadEliminidad(
                    participante.getEmails().stream().map(EmailEntity::getEmail).toList(),
                    actividad.getNombreActividad(),
                    actividad.getId().getFechaInicio().toString(),
                    actividad.getDireccionActividad().toString()
                );
            }
        }
        actividadRepository.deleteById(id);
    }

    @Transactional
    @Override
    public ActividadEntity inscribirUsuario(IdActividad idActividad, String cedulaUsuario)  {
      
        ActividadEntity actividad = this.actividadRepository.findById(idActividad).orElse(null);
           
        UsuarioEntity usuario = this.usuarioService.buscarPorCedula(cedulaUsuario).orElse(null);
       

        if (actividad.getCapacidad() > 0 && !actividad.getParticipantes().contains(usuario)) {

           actividad.getParticipantes().add(usuario);
           actividad.setCapacidad(actividad.getCapacidad() - 1);
        }  
        
        return actividadRepository.save(actividad);
    }

      @Scheduled(cron = "0 0 12 * * *") //se espera que se ejectute todos los dias alas 12
       public void enviarRecordatoriosActividades() {
        LocalDateTime mañana = LocalDateTime.now().plusDays(1).withHour(0).withMinute(0);
        
        List<ActividadEntity> actividadesManana = actividadRepository.buscarPorFecha(mañana);
        
        for (ActividadEntity actividad : actividadesManana) {
            for (UsuarioEntity participante : actividad.getParticipantes()) {
                emailService.enviarRecordatorio(
                    participante.getEmails().stream().map(EmailEntity::getEmail).toList(),
                    actividad.getNombreActividad(),
                    actividad.getId().getFechaInicio().toString(),
                    actividad.getDireccionActividad().toString()
                );
            }
        }

        
    }


        public List<InteresEntity> optenerIntereses(List<String> intereses) {
         List<InteresEntity> interesesEncontrados = new ArrayList<>();
         for (String interes : intereses) {
            Optional<InteresEntity> interesEncontrado = this.interesService.buscarPorNombre(interes);
            if (interesEncontrado.isPresent()) {
                interesesEncontrados.add(interesEncontrado.get());
            }
         }
        
         return interesesEncontrados;
        }
}
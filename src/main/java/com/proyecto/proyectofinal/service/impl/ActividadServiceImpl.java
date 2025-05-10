package com.proyecto.proyectofinal.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.proyectofinal.model.entities.ActividadEntity;
import com.proyecto.proyectofinal.model.entities.EmailEntity;
import com.proyecto.proyectofinal.model.entities.UsuarioEntity;
import com.proyecto.proyectofinal.model.idsEmbedded.IdActividad;
import com.proyecto.proyectofinal.repository.ActividadRepository;
import com.proyecto.proyectofinal.service.interfaces.ActividadService;
import com.proyecto.proyectofinal.service.interfaces.UsuarioService;

import jakarta.persistence.Id;



@Service
public class ActividadServiceImpl implements ActividadService {

    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private ActividadRepository actividadRepository;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Transactional
    @Override
    public ActividadEntity guardarActividad(ActividadEntity actividad) {
        IdActividad idActividad = actividad.getId();
        idActividad.setFechaCreacion(LocalDateTime.now());
        ActividadEntity actividadGuardada = this.actividadRepository.findById(actividad.getId())
            .orElse( actividadRepository.save(actividad));

        actividadGuardada.setId(idActividad);
        return actividadGuardada;
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
    public List<ActividadEntity> buscarEntreFechas(LocalDateTime inicio, LocalDateTime fin) {
        return actividadRepository.findActivitiesBetweenDates(inicio, fin);
    }
 
    @Transactional(readOnly = true)
    @Override
    public List<ActividadEntity> buscarProximasActividades() {
        return actividadRepository.buscarProximasActividades();
    }
    

    @Override
    public void eliminarActividad(IdActividad id) {
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
    @Transactional(readOnly = true)
    public void enviarRecordatoriosActividades() {
        LocalDateTime mañana = LocalDateTime.now().plusDays(1).withHour(0).withMinute(0);
        LocalDateTime pasadoMañana = mañana.plusDays(1);
        
        List<ActividadEntity> actividadesManana = actividadRepository.findActivitiesBetweenDates(mañana, pasadoMañana);
        
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
}
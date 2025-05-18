package com.proyecto.proyectofinal.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.proyecto.proyectofinal.mappers.MapperActividadResponse;
import com.proyecto.proyectofinal.model.dtos.requestDtos.RequestActividadDTO;
import com.proyecto.proyectofinal.model.dtos.responseDtos.ResponseActividadDTO;
import com.proyecto.proyectofinal.model.entities.ActividadEntity;
import com.proyecto.proyectofinal.model.entities.EmailEntity;
import com.proyecto.proyectofinal.model.entities.InteresEntity;
import com.proyecto.proyectofinal.model.entities.UsuarioEntity;
import com.proyecto.proyectofinal.repository.ActividadRepository;
import com.proyecto.proyectofinal.service.interfaces.ActividadService;
import com.proyecto.proyectofinal.service.interfaces.UsuarioService;




@Service
public class ActividadServiceImpl implements ActividadService {

    @Value ("${file.upload-dir.actividades}")
    String direccion;

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

    @Autowired
    private MapperActividadResponse mapper;
      @Transactional
    @Override
    public ActividadEntity guardarActividad(RequestActividadDTO actividadDto) {        
        actividadDto.setFechaCreacion(LocalDateTime.now());

        ActividadEntity actividad = new ActividadEntity();
        actividad.setFechaCreacion(actividadDto.getFechaCreacion());
        actividad.setFechaInicio(actividadDto.getFechaInicio());
        actividad.setNombreActividad(actividadDto.getNombre());
        actividad.setDescripcionActividad(actividadDto.getDescripcion());
        actividad.setCapacidad(actividadDto.getCapacidad());
        actividad.setDireccionActividad(this.direccionService.guardarDireccion(actividadDto.getDireccion(),actividadDto.getCiudad()));
        actividad.setCreador(this.usuarioService.buscarPorId(actividadDto.getCedulaCreador()));
        actividad.setInteresesActividad(optenerIntereses(actividadDto.getIntereses()));
        actividad.setImagenReferencia(guardarFoto(actividadDto.getFotoActividad()));
        return this.actividadRepository.save(actividad);

    }    @Transactional(readOnly = true)
    @Override
    public ResponseActividadDTO buscarPorId(LocalDateTime fechaCreacion) {
        return this.mapper.requestToResponse(this.actividadRepository.findById(fechaCreacion).get());
    }
     
    @Transactional(readOnly = true)
    @Override
    public List<ResponseActividadDTO> buscarPorCiudad(String ciudad) {
        return this.mapper.requestsToResponses(this.actividadRepository.buscarActividadPorCiudad(ciudad));  
    }

    @Transactional(readOnly = true)
    @Override
    public List<ResponseActividadDTO> buscarPorInteres(String interes) {
        return this.mapper.requestsToResponses(actividadRepository.buscarActividadesPorInteres(interes));
    }
    @Transactional(readOnly = true)
    @Override
    public List<ResponseActividadDTO> buscarPorCreador(String cedula) {
        return this.mapper.requestsToResponses(actividadRepository.buscarActividadesPorCreador(cedula));
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<ResponseActividadDTO> buscarEntreFechas(LocalDateTime inicio) {
        return this.mapper.requestsToResponses(actividadRepository.buscarPorFecha(inicio));
    }
 
    @Transactional(readOnly = true)
    @Override
    public List<ResponseActividadDTO> buscarProximasActividades() {
        return this.mapper.requestsToResponses(actividadRepository.buscarProximasActividades());  
    }
        @Override
    public void eliminarActividad(LocalDateTime fechaCreacion) {
        if (this.actividadRepository.existsById(fechaCreacion)) {
            ActividadEntity actividad = this.actividadRepository.findById(fechaCreacion).orElse(null);
            for (UsuarioEntity participante : actividad.getParticipantes()) {
                emailService.recordatorioActividadEliminidad(
                    participante.getEmails().stream().map(EmailEntity::getEmail).toList(),
                    actividad.getNombreActividad(),
                    actividad.getFechaInicio().toString(),
                    actividad.getDireccionActividad().toString()
                );
            }
        }
        actividadRepository.deleteById(fechaCreacion);
    }    @Transactional
    @Override
    public ActividadEntity inscribirUsuario(LocalDateTime fechaCreacion, String cedulaUsuario)  {
      
        ActividadEntity actividad = this.actividadRepository.findById(fechaCreacion).orElse(null);
           
        UsuarioEntity usuario = this.usuarioService.buscarPorId(cedulaUsuario);
       

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
            for (UsuarioEntity participante : actividad.getParticipantes()) {                emailService.enviarRecordatorio(
                    participante.getEmails().stream().map(EmailEntity::getEmail).toList(),
                    actividad.getNombreActividad(),
                    actividad.getFechaInicio().toString(),
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

        public String guardarFoto(MultipartFile archivo) {
         if (archivo == null || archivo.isEmpty()) {
            return null;
         }
         try {
            File directorio = new File(direccion);
            if (!directorio.exists()) {
                directorio.mkdirs();
            }

            String nombreOriginal = archivo.getOriginalFilename();
            String extension = nombreOriginal.substring(nombreOriginal.lastIndexOf("."));
            String nombreArchivo = System.currentTimeMillis() + extension;

            Path path = Paths.get(direccion, nombreArchivo);
            Files.write(path, archivo.getBytes());

            return "/actividades/" + nombreArchivo;
         } catch (Exception e) {
            System.out.println("aqui esta el error: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

        @Override
        public List<ResponseActividadDTO> buscarPorNombreActividad(String nombre) {
            return this.mapper.requestsToResponses(this.actividadRepository.findByNombreActividadContainingIgnoreCase(nombre));
        }

        @Override
        public List<ResponseActividadDTO> buscarPorParticipante(String cedula) {
           return this.mapper.requestsToResponses(this.actividadRepository.buscarActividadesPorParticipante(cedula));
        }        @Override
        public void actualizarActividad(LocalDateTime fechaCreacion, RequestActividadDTO actividadDto) throws IOException {
            ActividadEntity actividad = this.actividadRepository.findById(fechaCreacion)
                .orElseThrow(() -> new RuntimeException("No se encontró la actividad con fecha de creación: " + fechaCreacion));
            
            actividad.setNombreActividad(actividadDto.getNombre());
            actividad.setDescripcionActividad(actividadDto.getDescripcion());
            actividad.setCapacidad(actividadDto.getCapacidad());
            actividad.setFechaInicio(actividadDto.getFechaInicio());
            actividad.setDireccionActividad(this.direccionService.guardarDireccion(actividadDto.getDireccion(),actividadDto.getCiudad()));
            actividad.setCreador(this.usuarioService.buscarPorId(actividadDto.getCedulaCreador()));
            actividad.setInteresesActividad(optenerIntereses(actividadDto.getIntereses()));
            if (actividadDto.getFotoActividad() != null) {
                // Eliminar la foto anterior si existe
                String rutaAnterior = actividad.getImagenReferencia();
                if (rutaAnterior != null) {
                    Path path = Paths.get(direccion, rutaAnterior);
                    Files.deleteIfExists(path);
                }
                // Guardar la nueva foto
                actividad.setImagenReferencia(guardarFoto(actividadDto.getFotoActividad()));
            }
            this.actividadRepository.save(actividad);
        }
}
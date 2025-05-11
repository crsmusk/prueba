package com.proyecto.proyectofinal.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.proyectofinal.model.entities.EmailEntity;
import com.proyecto.proyectofinal.repository.EmailRepository;
import com.proyecto.proyectofinal.service.interfaces.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private JavaMailSender emailSender;

    @Transactional(readOnly = true)
    public void enviarRecordatorio(List<String> destinatarios, String nombreActividad, String fecha, String ubicacion) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(destinatarios.toArray(new String[0]));
        message.setSubject("Recordatorio: Actividad mañana - " + nombreActividad);
        message.setText("Hola,\n\nTe recordamos que mañana tienes la actividad '" + 
                       nombreActividad + "' en " + ubicacion + " a las " + fecha + 
                       ".\n\n¡Te esperamos!");
        
        emailSender.send(message);
    }
    
    @Transactional(readOnly = true)
    public void recordatorioActividadEliminidad(List<String> destinatarios,String nombreActividad, String fecha, String ubicacion){
         SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(destinatarios.toArray(new String[0]));
        message.setSubject("Recordatorio: La actividad - " + nombreActividad);
        message.setText("Hola,\n\nTe recordamos que la actividad fue canceladad '" + 
                       nombreActividad + "' en " + ubicacion + " a las " + fecha + 
                       ".\n\n¡Te esperamos!");
        
        emailSender.send(message);
    }

    @Override
    @Transactional
    public EmailEntity guardarEmail(EmailEntity email) {
        EmailEntity emailGuardado = emailRepository
            .findByEmail(email.getEmail())
            .orElse(emailRepository.save(email));
        return emailGuardado;
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<EmailEntity> buscarPorEmail(String email) {
        return emailRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public void eliminarEmail(String email) {
        emailRepository.deleteById(email);
    }

    
}

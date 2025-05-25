package com.proyecto.proyectofinal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto.proyectofinal.repository.RolRepository;
import com.proyecto.proyectofinal.repository.InteresRepository;
import com.proyecto.proyectofinal.model.entities.RolEntity;
import com.proyecto.proyectofinal.model.entities.InteresEntity;
import jakarta.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Service
public class InitDataService {
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private InteresRepository interesRepository;

    @PostConstruct
    public void init() {
        if (rolRepository.count() == 0) {
            rolRepository.saveAll(Arrays.asList(
                new RolEntity("ADMIN"),
                new RolEntity("USER")
            ));
        }
        if (interesRepository.count() == 0) {
            List<InteresEntity> intereses = Arrays.asList(
                new InteresEntity("musica", null),
                new InteresEntity("deportes", null),
                new InteresEntity("cine", null),
                new InteresEntity("lectura", null),
                new InteresEntity("viajes", null),
                new InteresEntity("tecnologia", null),
                new InteresEntity("arte", null),
                new InteresEntity("cocina", null),
                new InteresEntity("fotografia", null),
                new InteresEntity("otros", null)
            );
            interesRepository.saveAll(intereses);
        }
    }
}

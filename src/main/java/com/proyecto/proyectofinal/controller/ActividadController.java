package com.proyecto.proyectofinal.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyecto.proyectofinal.model.dtos.requestDtos.RequestUsuarioDTO;
import com.proyecto.proyectofinal.service.interfaces.ActividadService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/actividad")
public class ActividadController {
  
    @Autowired
    private ActividadService actividadService;

   @GetMapping("/ver")
    public String mostrarFormularioActividades(Model model) {
        //model.addAttribute("usuarioDto", new RequestUsuarioDTO());
        return "mostrar";
    }
    
    
}

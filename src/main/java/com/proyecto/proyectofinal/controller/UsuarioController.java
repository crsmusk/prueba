package com.proyecto.proyectofinal.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyecto.proyectofinal.model.dtos.requestDtos.RequestUsuarioDTO;
import com.proyecto.proyectofinal.service.interfaces.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuarioDto", new RequestUsuarioDTO());
        return "index";
    }

    @PostMapping("/guardar")
    public String guardarUsuario(@ModelAttribute("usuarioDto") RequestUsuarioDTO usuarioDto, 
                                RedirectAttributes redirectAttributes) {
       
            //proceso de llenado de los campos
            /*
             * primerp se verifica que los campos no sean nulos  y que no esten vacios
             * despues  atraves de un stream se separan los valores por comas y se convierten a una lista
             * se podria considerar usar trim para los espacios
            */
            if (usuarioDto.getTelefonos() != null && !usuarioDto.getTelefonos().isEmpty()) {
                List<String> telefonos = Arrays.stream(usuarioDto.getTelefonos().get(0).split(","))
                    .collect(Collectors.toList());
                usuarioDto.setTelefonos(telefonos);
            }
            
            if (usuarioDto.getEmails() != null && !usuarioDto.getEmails().isEmpty()) {
                List<String> emails = Arrays.stream(usuarioDto.getEmails().get(0).split(","))
                    .collect(Collectors.toList());
                usuarioDto.setEmails(emails);
            }
            
            if (usuarioDto.getIntereses() != null && !usuarioDto.getIntereses().isEmpty()) {
                List<String> intereses = Arrays.stream(usuarioDto.getIntereses().get(0).split(","))
                    .collect(Collectors.toList());
                usuarioDto.setIntereses(intereses);
            }
            
            if (usuarioDto.getRoles() != null && !usuarioDto.getRoles().isEmpty()) {
                List<String> roles = Arrays.stream(usuarioDto.getRoles().get(0).split(","))
                    .collect(Collectors.toList());
                usuarioDto.setRoles(roles);
            }

            usuarioService.guardarUsuario(usuarioDto);
            return "redirect:/usuario/registro";
        
    }
}
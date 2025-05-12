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
        this.usuarioService.guardarUsuario(usuarioDto);
        
        return "redirect:/usuario/registro";
    }
}
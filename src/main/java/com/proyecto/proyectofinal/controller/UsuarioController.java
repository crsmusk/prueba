package com.proyecto.proyectofinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
        return "crearUsuario";
    }

    @GetMapping("/perfil-creador/{cedula}")
    public String mostrarPerfilCreador(@PathVariable String cedula, Model model) {
        model.addAttribute("usuario", this.usuarioService.buscarPorCedula(cedula));

        return "infoUsuarioCreador";
    }

    @GetMapping("/perfil-normal/{nickName}")
    public String mostrarPerfilNormal(@PathVariable String nickName, Model model) {
        model.addAttribute("usuario", this.usuarioService.buscarPorNickname(nickName));

        return "infoUsuarioNormal";
    }

    @PostMapping("/guardar")
    public String guardarUsuario(@ModelAttribute("usuarioDto") RequestUsuarioDTO usuarioDto,
            RedirectAttributes redirectAttributes) {
        this.usuarioService.guardarUsuario(usuarioDto);

        return "redirect:/usuario/crearUsuario";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/borrar-usuario/{cedula}")
    public String borrarUsuario(@PathVariable String cedula, Model model) {
        this.usuarioService.eliminarUsuario(cedula);
        return "redirect:/actividad/listar-actividades";
    }

}
package com.Uisrael.SistemaVeterinaria.controller;

import com.Uisrael.SistemaVeterinaria.entities.Usuario;
import com.Uisrael.SistemaVeterinaria.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VeterinarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/veterinario")
    public String mostrarVeterinario(Model model) {
        try {
            // Obtener el usuario autenticado
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();  // Asumiendo que el nombre de usuario es único

            // Obtener los datos del usuario usando su nombre de usuario
            Usuario usuario = usuarioRepository.findByUsername(username);

            if (usuario != null) {
                // Agregar el usuario al modelo
                model.addAttribute("usuario", usuario);
            } else {
                model.addAttribute("error", "No se encontró la información del usuario.");
            }
        } catch (Exception e) {
            model.addAttribute("error", "Error al obtener la información del usuario: " + e.getMessage());
        }
        return "veterinario"; // Nombre de la vista HTML (veterinario.html)
    }
}

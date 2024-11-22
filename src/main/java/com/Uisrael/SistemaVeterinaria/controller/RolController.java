package com.Uisrael.SistemaVeterinaria.controller;

import com.Uisrael.SistemaVeterinaria.entities.Cita;
import com.Uisrael.SistemaVeterinaria.entities.Rol;
import com.Uisrael.SistemaVeterinaria.service.CitaService;
import com.Uisrael.SistemaVeterinaria.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RolController {

    @Autowired
    private RolService rolService;
    @Autowired
    private CitaService citaService;


    // Listar todas las roles
    @GetMapping("/rol")
    public String listarRols(Model model) {
        try {
            List<Rol> rol = rolService.obtenerTodos();  // Obtener todas las rols
            List<Cita> citasHoy = citaService.obtenerCitasDeHoy();
            model.addAttribute("citasHoy", citasHoy);
            model.addAttribute("listarRoles", rol);
            model.addAttribute("rol", new Rol());
        } catch (Exception e) {
            model.addAttribute("error", "Error al obtener las roles: " + e.getMessage());
        }
        return "ModuloUsuario/rol";  // Nombre de la vista HTML (rol.html)
    }
}

package com.Uisrael.SistemaVeterinaria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {

    @GetMapping("/")
    public String home() {
        return "redirect:/Inicio";
    }

    @GetMapping("/Inicio")
    public String inicio() {
        return "Inicio";  // Retorna la vista 'Inicio.html' en la carpeta 'templates'
    }
}

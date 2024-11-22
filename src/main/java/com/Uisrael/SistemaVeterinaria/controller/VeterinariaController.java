package com.Uisrael.SistemaVeterinaria.controller;

import com.Uisrael.SistemaVeterinaria.entities.Cita;
import com.Uisrael.SistemaVeterinaria.entities.Veterinaria;
import com.Uisrael.SistemaVeterinaria.repositories.VeterinariaRepository;
import com.Uisrael.SistemaVeterinaria.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class VeterinariaController {

    @Autowired
    private VeterinariaRepository veterinariaRepository;
    @Autowired
    private CitaService citaService;


    @GetMapping("/veterinaria")
    public String mostrarVeterinaria(Model model) {
        List<Veterinaria> veterinarias = veterinariaRepository.findAll();
        List<Cita> citasHoy = citaService.obtenerCitasDeHoy();

        model.addAttribute("citasHoy", citasHoy);
        model.addAttribute("veterinarias", veterinarias);
        return "ModuloVeterinaria/veterinaria"; // Nombre de la vista

    }

}

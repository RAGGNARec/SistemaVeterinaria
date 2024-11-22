package com.Uisrael.SistemaVeterinaria.controller;

import com.Uisrael.SistemaVeterinaria.entities.*;
import com.Uisrael.SistemaVeterinaria.service.CitaService;
import com.Uisrael.SistemaVeterinaria.service.EspecieService;
import com.Uisrael.SistemaVeterinaria.service.RazaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class RazaController {

    @Autowired
    private RazaService razaService;

    @Autowired
    private EspecieService especieService;

    @Autowired
    private CitaService citaService;


    // Listar todas las razas
    @GetMapping("/raza")
    public String listarRazas(Model model) {
        try {
            List<Raza> raza = razaService.obtenerTodos();  // Obtener todas las razas
            List<Especie> especie = especieService.obtenerTodos();
            List<Cita> citasHoy = citaService.obtenerCitasDeHoy();
            model.addAttribute("citasHoy", citasHoy);
            model.addAttribute("listarRazas", raza);
            model.addAttribute("especies", especie);
            model.addAttribute("raza", new Raza());
        } catch (Exception e) {
            model.addAttribute("error", "Error al obtener las razas: " + e.getMessage());
        }
        return "ModuloMascota/raza";  // Nombre de la vista HTML (raza.html)
    }


    // Guardar nueva raza
    @PostMapping("/raza/guardar")
    public String guardarNuevaRaza(@ModelAttribute Raza raza, RedirectAttributes redirectAttributes) {
        try {
            razaService.crear(raza);  // Guardar la raza (nuevo o editado)
            redirectAttributes.addFlashAttribute("message", "La raza ha sido guardada con éxito");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Error al guardar la raza: " + e.getMessage());
        }
        return "redirect:/raza"; // Redirigir a la lista de razas
    }

    // Editar una raza
    @GetMapping("/raza/editar/{id}")
    public String EditarRaza(@PathVariable Long id, Model model) {
        // Obtener la raza por su ID
        Raza raza = razaService.obtenerPorId(id);

        // Verificar si la raza existe
        if (raza == null) {
            model.addAttribute("error", "Raza no encontrada.");
            return "redirect:/raza"; // Redirigir a la lista de servicios
        }

        // Obtener las listas de mascotas y servicios
        List<Especie> especies = especieService.obtenerTodos();

        // Agregar los datos al modelo
        model.addAttribute("especies", especies);
        model.addAttribute("raza", raza);

        return "ModuloMascota/formEditarRaza"; // Retorna la vista con el formulario de edición
    }


}

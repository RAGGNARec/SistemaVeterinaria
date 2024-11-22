package com.Uisrael.SistemaVeterinaria.controller;

import com.Uisrael.SistemaVeterinaria.entities.Cita;
import com.Uisrael.SistemaVeterinaria.entities.Especie;
import com.Uisrael.SistemaVeterinaria.service.CitaService;
import com.Uisrael.SistemaVeterinaria.service.EspecieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class EspecieController {

    @Autowired
    private EspecieService especieService;
    @Autowired
    private CitaService citaService;


    // Listar todas las especies
    @GetMapping("/especie")
    public String listarEspecies(Model model) {
        try {
            List<Especie> especie = especieService.obtenerTodos();  // Obtener todas las especies
            List<Cita> citasHoy = citaService.obtenerCitasDeHoy();
            model.addAttribute("citasHoy", citasHoy);
            model.addAttribute("listarEspecies", especie);
            model.addAttribute("especie", new Especie());
        } catch (Exception e) {
            model.addAttribute("error", "Error al obtener las especies: " + e.getMessage());
        }
        return "ModuloMascota/especie";  // Nombre de la vista HTML (especie.html)
    }


    // Guardar nueva especie
    @PostMapping("/especie/guardar")
    public String guardarNuevaEspecie(@ModelAttribute Especie especie, RedirectAttributes redirectAttributes) {
        try {
            especieService.crear(especie);  // Guardar la especie (nuevo o editado)
            redirectAttributes.addFlashAttribute("message", "La especie ha sido guardada con éxito");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Error al guardar la especie: " + e.getMessage());
        }
        return "redirect:/especie"; // Redirigir a la lista de especies
    }

    // Editar una especie
    @GetMapping("/especie/editar/{id}")
    public String editarEspecie(@PathVariable Long id, Model model) {
        // Obtener el especie por su ID
        Especie especie = especieService.obtenerPorId(id);
        // Verificar si el especie existe
        if (especie == null) {
            model.addAttribute("error", "Especie no encontrado.");
            return "redirect:/especie"; // Redirigir a la lista de especies
        }
        // Agregar el especie al modelo
        model.addAttribute("especie", especie);
        return "ModuloMascota/formEditarEspecie"; // Retorna la vista con el formulario de edición
    }
}
package com.Uisrael.SistemaVeterinaria.controller;

import com.Uisrael.SistemaVeterinaria.entities.Cita;
import com.Uisrael.SistemaVeterinaria.entities.HistorialVacunacion;
import com.Uisrael.SistemaVeterinaria.entities.Mascota;
import com.Uisrael.SistemaVeterinaria.service.CitaService;
import com.Uisrael.SistemaVeterinaria.service.MascotaService;
import com.Uisrael.SistemaVeterinaria.service.HistorialVacunacionService;
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
public class HistorialVacunacionController {

    @Autowired
    private HistorialVacunacionService historialVacunacionService;

    @Autowired
    private MascotaService mascotaService;

    @Autowired
    private CitaService citaService;


    // Listar todas las historialVacunacions
    @GetMapping("/historialVacunacion")
    public String listarHistorialVacunacions(Model model) {
        try {
            List<HistorialVacunacion> historialVacunacion = historialVacunacionService.obtenerTodos();  // Obtener todas las historialVacunacions
            List<Mascota> mascota = mascotaService.obtenerTodos();
            List<Cita> citasHoy = citaService.obtenerCitasDeHoy();
            model.addAttribute("citasHoy", citasHoy);
            model.addAttribute("listarHistorialVacunacion", historialVacunacion);
            model.addAttribute("mascotas", mascota);
            model.addAttribute("historialVacunacion", new HistorialVacunacion());
        } catch (Exception e) {
            model.addAttribute("error", "Error al obtener las historialVacunacion: " + e.getMessage());
        }
        return "ModuloMascota/historialVacunacion";  // Nombre de la vista HTML (historialVacunacion.html)
    }


    // Guardar nueva historialVacunacion
    @PostMapping("/historialVacunacion/guardar")
    public String guardarNuevaHistorialVacunacion(@ModelAttribute HistorialVacunacion historialVacunacion, RedirectAttributes redirectAttributes) {
        try {
            historialVacunacionService.crear(historialVacunacion);  // Guardar la historialVacunacion (nuevo o editado)
            redirectAttributes.addFlashAttribute("message", "El historial Vacunacion ha sido guardada con éxito");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Error al guardar la historialVacunacion: " + e.getMessage());
        }
        return "redirect:/historialVacunacion"; // Redirigir a la lista de historialVacunacions
    }

    // Editar una historialVacunacion
    @GetMapping("/historialVacunacion/editar/{id}")
    public String EditarClinico(@PathVariable Long id, Model model) {
        // Obtener la raza por su ID
        HistorialVacunacion historialVacunacion = historialVacunacionService.obtenerPorId(id);

        // Verificar si el historial clinico existe
        if (historialVacunacion == null) {
            model.addAttribute("error", "Historial clinico no encontrada.");
            return "redirect:/historialVacunacion"; // Redirigir a la lista de servicios
        }

        // Obtener las listas de mascotas y servicios
        List<Mascota> mascotas = mascotaService.obtenerTodos();

        // Agregar los datos al modelo
        model.addAttribute("mascotas", mascotas);
        model.addAttribute("historialVacunacion", historialVacunacion);

        return "ModuloMascota/formEditarVacunacion"; // Retorna la vista con el formulario de edición
    }


}


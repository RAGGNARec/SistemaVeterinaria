package com.Uisrael.SistemaVeterinaria.controller;

import com.Uisrael.SistemaVeterinaria.entities.*;
import com.Uisrael.SistemaVeterinaria.service.CitaService;
import com.Uisrael.SistemaVeterinaria.service.MascotaService;
import com.Uisrael.SistemaVeterinaria.service.HistorialClinicoService;
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
public class HistorialClinicoController {

    @Autowired
    private HistorialClinicoService historialClinicoService;

    @Autowired
    private MascotaService mascotaService;

    @Autowired
    private CitaService citaService;


    // Listar todas las historialClinicos
    @GetMapping("/historialClinico")
    public String listarHistorialClinicos(Model model) {
        try {
            List<HistorialClinico> historialClinico = historialClinicoService.obtenerTodos();  // Obtener todas las historialClinicos
            List<Mascota> mascota = mascotaService.obtenerTodos();
            List<Cita> citasHoy = citaService.obtenerCitasDeHoy();
            model.addAttribute("citasHoy", citasHoy);
            model.addAttribute("listarHistorialClinicos", historialClinico);
            model.addAttribute("mascotas", mascota);
            model.addAttribute("historialClinico", new HistorialClinico());
        } catch (Exception e) {
            model.addAttribute("error", "Error al obtener las historialClinicos: " + e.getMessage());
        }
        return "ModuloMascota/historialClinico";  // Nombre de la vista HTML (historialClinico.html)
    }


    // Guardar nueva historialClinico
    @PostMapping("/historialClinico/guardar")
    public String guardarNuevaHistorialClinico(@ModelAttribute HistorialClinico historialClinico, RedirectAttributes redirectAttributes) {
        try {
            historialClinicoService.crear(historialClinico);  // Guardar la historialClinico (nuevo o editado)
            redirectAttributes.addFlashAttribute("message", "El historial Clinico ha sido guardada con éxito");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Error al guardar la historialClinico: " + e.getMessage());
        }
        return "redirect:/historialClinico"; // Redirigir a la lista de historialClinicos
    }

    // Editar una historialClinico
    @GetMapping("/historialClinico/editar/{id}")
    public String EditarClinico(@PathVariable Long id, Model model) {
        // Obtener la raza por su ID
        HistorialClinico historialClinico = historialClinicoService.obtenerPorId(id);

        // Verificar si el historial clinico existe
        if (historialClinico == null) {
            model.addAttribute("error", "Historial clinico no encontrada.");
            return "redirect:/historialClinico"; // Redirigir a la lista de servicios
        }

        // Obtener las listas de mascotas y servicios
        List<Mascota> mascotas = mascotaService.obtenerTodos();

        // Agregar los datos al modelo
        model.addAttribute("mascotas", mascotas);
        model.addAttribute("historialClinico", historialClinico);

        return "ModuloMascota/formEditarClinico"; // Retorna la vista con el formulario de edición
    }

    // Cambio de estado del mascota (activar/desactivar)
    @GetMapping("/historialClinico/cambiarEstado/{id}")
    public String cambiarEstadoHistorialClinico(@PathVariable Long id) {
        // Llamar al servicio para cambiar el estado
        historialClinicoService.cambiarEstado(id);
        return "redirect:/historialClinico"; // Redirigir a la lista de ciente
    }

}


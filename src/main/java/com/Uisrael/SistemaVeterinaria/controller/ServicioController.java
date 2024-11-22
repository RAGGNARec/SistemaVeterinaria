package com.Uisrael.SistemaVeterinaria.controller;

import com.Uisrael.SistemaVeterinaria.entities.Cita;
import com.Uisrael.SistemaVeterinaria.entities.Servicio;
import com.Uisrael.SistemaVeterinaria.service.CitaService;
import com.Uisrael.SistemaVeterinaria.service.ServicioService;
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
public class ServicioController {
    @Autowired
    private ServicioService servicioService;

    @Autowired
    private CitaService citaService;


    // Listar todas las servicios
    @GetMapping("/servicio")
    public String listarServicios(Model model) {
        try {
            List<Servicio> servicio = servicioService.obtenerTodos();  // Obtener todas las servicios
            List<Cita> citasHoy = citaService.obtenerCitasDeHoy();
            model.addAttribute("citasHoy", citasHoy);
            model.addAttribute("listarServicios", servicio);
            model.addAttribute("servicio", new Servicio());
        } catch (Exception e) {
            model.addAttribute("error", "Error al obtener las servicios: " + e.getMessage());
        }
        return "ModuloProducto/servicio";  // Nombre de la vista HTML (servicio.html)
    }


    // Guardar nueva servicio
    @PostMapping("/servicio/guardar")
    public String guardarServicio(@ModelAttribute Servicio servicio, RedirectAttributes redirectAttributes) {
        try {
            // Guardar o actualizar el servicio en la base de datos
            servicioService.crear(servicio);
            redirectAttributes.addFlashAttribute("message", "Servicio guardado correctamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Error al guardar el servicio: " + e.getMessage());
        }
        return "redirect:/servicio"; // Redirigir a la lista de servicios
    }


    // Editar una servicio
    @GetMapping("/servicio/editar/{id}")
    public String editarServicio(@PathVariable Long id, Model model) {
        // Obtener el servicio por su ID
        Servicio servicio = servicioService.obtenerPorId(id);
        // Verificar si el servicio existe
        if (servicio == null) {
            model.addAttribute("error", "Servicio no encontrado.");
            return "redirect:/servicio"; // Redirigir a la lista de servicios
        }
        // Agregar el servicio al modelo
        model.addAttribute("servicio", servicio);
        return "ModuloProducto/formEditar"; // Retorna la vista con el formulario de edición
    }
}
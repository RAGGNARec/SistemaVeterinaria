package com.Uisrael.SistemaVeterinaria.controller;

import com.Uisrael.SistemaVeterinaria.entities.Cita;
import com.Uisrael.SistemaVeterinaria.entities.Mascota;
import com.Uisrael.SistemaVeterinaria.entities.Servicio;
import com.Uisrael.SistemaVeterinaria.service.CitaService;
import com.Uisrael.SistemaVeterinaria.service.MascotaService;
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
public class CitaController {

    @Autowired
    private CitaService citaService;

    @Autowired
    private MascotaService mascotaService;

    @Autowired
    private ServicioService servicioService;


    @GetMapping("/calendario")
    public String mostrarCalendario(Model model) {

        List<Cita> citasHoy = citaService.obtenerCitasDeHoy();
        model.addAttribute("citasHoy", citasHoy);
        return "ModuloCita/calendario"; // Redirige a la vista HTML de Calendario
    }

    // Listar todas las citas
    @GetMapping("/cita")
    public String listarCitas(Model model) {
        try {
            List<Cita> cita = citaService.obtenerTodos();  // Obtener todas las citas
            List<Mascota> mascotas = mascotaService.obtenerTodos();
            List<Servicio> servicios = servicioService.obtenerTodos();
            List<Cita> citasHoy = citaService.obtenerCitasDeHoy();
            model.addAttribute("citasHoy", citasHoy);

            model.addAttribute("listarCitas", cita);
            model.addAttribute("cita", new Cita());
            model.addAttribute("mascotas" , mascotas);
            model.addAttribute("servicios", servicios);
        } catch (Exception e) {
            model.addAttribute("error", "Error al obtener las citas: " + e.getMessage());
        }
        return "ModuloCita/cita";  // Nombre de la vista HTML (cita.html)
    }

    // Guardar nuevo cita
    @PostMapping("/cita/guardar")
    public String guardarNuevaCita(@ModelAttribute Cita cita, RedirectAttributes redirectAttributes) {
        try {
            citaService.crear(cita);  // Guardar la cita (nuevo o editado)
            redirectAttributes.addFlashAttribute("message", "La cita ha sido guardada con éxito");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Error al guardar la cita: " + e.getMessage());
        }
        return "redirect:/cita"; // Redirigir a la lista de citas
    }

    @GetMapping("/cita/editar/{id}")
    public String EditarCita(@PathVariable Long id, Model model) {
        // Obtener la cita por su ID
        Cita cita = citaService.obtenerPorId(id);

        // Verificar si la cita existe
        if (cita == null) {
            model.addAttribute("error", "Cita no encontrada.");
            return "redirect:/cita"; // Redirigir a la lista de servicios
        }

        // Obtener las listas de mascotas y servicios
        List<Mascota> mascotas = mascotaService.obtenerTodos();
        List<Servicio> servicios = servicioService.obtenerTodos();

        // Agregar los datos al modelo
        model.addAttribute("mascotas", mascotas);
        model.addAttribute("servicios", servicios);
        model.addAttribute("cita", cita);

        return "ModuloCita/formEditar"; // Retorna la vista con el formulario de edición
    }


    // Cambio de estado de la mascota (PENDIENTE -> COMPLETADA -> CANCELADA)
    @GetMapping("/cita/cambiarEstado/{id}")
    public String cambiarEstadoCita(@PathVariable Long id) {
        // Llamar al servicio para cambiar el estado de la mascota
        citaService.cambiarEstado(id);
        return "redirect:/cita"; // Redirigir a la lista de mascotas
    }

    // Cancelar la cita (cambia el estado a CANCELADA)
    @GetMapping("/cita/cancelar/{id}")
    public String cancelarCita(@PathVariable Long id) {
        // Llamar al servicio para cancelar la cita
        citaService.cancelarCita(id);
        return "redirect:/cita"; // Redirigir a la lista de citas
    }

}

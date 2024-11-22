package com.Uisrael.SistemaVeterinaria.controller;

import com.Uisrael.SistemaVeterinaria.entities.*;
import com.Uisrael.SistemaVeterinaria.service.*;
import com.Uisrael.SistemaVeterinaria.service.RazaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RazaService razaService;

    @Autowired
    private EspecieService especieService;

    @Autowired
    private CitaService citaService;


    // Listar todas las mascotas
    @GetMapping("/mascota")
    public String listarMascotas(Model model) {
        try {
            List<Mascota> mascota = mascotaService.obtenerTodos();  // Obtener todas las mascotas
            List<Raza> razas = razaService.obtenerTodos();
            List<Usuario> usuarios = usuarioService.obtenerTodos();  // Obtener todos los usuarios
            //List<Especie> especies = especieService.obtenerTodos();  // Obtener todas las especies
            List<Cita> citasHoy = citaService.obtenerCitasDeHoy();
            model.addAttribute("citasHoy", citasHoy);

            model.addAttribute("listarMascotas", mascota);
            model.addAttribute("mascota", new Mascota());
            model.addAttribute("razas", razas);
            model.addAttribute("usuarios", usuarios);  // Añadir usuarios al modelo
            //model.addAttribute("especies", especies);  // Añadir especies al modelo

        } catch (Exception e) {
            model.addAttribute("error", "Error al obtener las mascotas: " + e.getMessage());
        }
        return "ModuloMascota/mascota";  // Nombre de la vista HTML (mascota.html)
    }


    // Guardar nuevo mascota
    @PostMapping("/mascota/guardar")
    public String guardarNuevaMascota(@ModelAttribute Mascota mascota,
                                      RedirectAttributes redirectAttributes) {
        try {
            mascotaService.crear(mascota);  // Guardar la mascota (nuevo o editado)
            redirectAttributes.addFlashAttribute("message", "La mascota ha sido guardada con éxito");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Error al guardar la mascota: " + e.getMessage());
        }
        return "redirect:/mascota"; // Redirigir a la lista de mascotas
    }


    // Editar un mascota
    @GetMapping("/mascota/editar/{id}")
    public String EditarMascota(@PathVariable Long id, Model model) {
        // Obtener la raza por su ID
        Mascota mascota = mascotaService.obtenerPorId(id);

        // Verificar si el historial clinico existe
        if (mascota == null) {
            model.addAttribute("error", "Mascota no encontrada.");
            return "redirect:/mascota"; // Redirigir a la lista de servicios
        }

        // Obtener las listas de mascotas y servicios
        List<Usuario> usuarios = usuarioService.obtenerTodos();  // Obtener todos los usuarios
        List<Raza> razas = razaService.obtenerTodos();

        // Agregar los datos al modelo
        model.addAttribute("usuarios", usuarios);  // Añadir usuarios al modelo
        model.addAttribute("mascota", mascota);
        model.addAttribute("razas", razas);

        return "ModuloMascota/formEditarMascota"; // Retorna la vista con el formulario de edición
    }


    // Cambio de estado del mascota (activar/desactivar)
    @GetMapping("/mascota/cambiarEstado/{id}")
    public String cambiarEstadoMascota(@PathVariable Long id) {
        // Llamar al servicio para cambiar el estado
        mascotaService.cambiarEstado(id);
        return "redirect:/mascota"; // Redirigir a la lista de ciente
    }


}


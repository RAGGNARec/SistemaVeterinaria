package com.Uisrael.SistemaVeterinaria.controller;

import com.Uisrael.SistemaVeterinaria.entities.*;
import com.Uisrael.SistemaVeterinaria.repositories.UsuarioRepository;
import com.Uisrael.SistemaVeterinaria.service.CitaService;
import com.Uisrael.SistemaVeterinaria.service.HistorialClinicoService;
import com.Uisrael.SistemaVeterinaria.service.HistorialVacunacionService;
import com.Uisrael.SistemaVeterinaria.service.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ClienteController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MascotaService mascotaService;  // Usa la interfaz MascotaService

    @Autowired
    private HistorialClinicoService historialClinicoService;

    @Autowired
    private HistorialVacunacionService historialVacunacionService;

    @Autowired
    private CitaService citaService;


    @GetMapping("/cliente")
    public String mostrarCliente(Model model) {
        try {
            // Obtener el usuario autenticado
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();  // Asumiendo que el nombre de usuario es único

            // Obtener los datos del usuario usando su nombre de usuario
            Usuario usuario = usuarioRepository.findByUsername(username);

            if (usuario != null) {
                // Obtener las mascotas del usuario autenticado
                List<Mascota> mascotas = mascotaService.obtenerMascotasPorCliente(usuario);

                // Agregar el usuario y las mascotas al modelo
                model.addAttribute("usuario", usuario);
                model.addAttribute("mascotas", mascotas);  // Pasar las mascotas al modelo
            } else {
                model.addAttribute("error", "No se encontró la información del usuario.");
            }
        } catch (Exception e) {
            model.addAttribute("error", "Error al obtener la información del usuario: " + e.getMessage());
        }
        return "cliente"; // Nombre de la vista HTML (cliente.html)
    }

    @GetMapping("/mascota/ver/{id}")
    public String verMascota(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Mascota mascota = mascotaService.obtenerPorId(id);
            if (mascota != null) {
                List<HistorialClinico> historialClinico = historialClinicoService.obtenerPorMascota(mascota);
                List<HistorialVacunacion> historialVacuna = historialVacunacionService.obtenerPorMascota(mascota);


                model.addAttribute("mascota", mascota);
                model.addAttribute("historialClinico", historialClinico);
                model.addAttribute("historialVacuna", historialVacuna);
                return "ModuloCliente/verMascota";
            } else {
                redirectAttributes.addFlashAttribute("error", "No se encontró la mascota.");
                return "redirect:/cliente";
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error interno: " + e.getMessage());
            return "redirect:/cliente";
        }
    }


    @GetMapping("/mascota/verHistorial/{id}")
    public String verMascotaHistorial(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Mascota mascota = mascotaService.obtenerPorId(id);
            if (mascota != null) {
                List<Cita> cita = citaService.obtenerPorMascota(mascota);

                model.addAttribute("mascota", mascota);
                model.addAttribute("cita", cita);
                return "ModuloCliente/verMascotaHistorial";
            } else {
                redirectAttributes.addFlashAttribute("error", "No se encontró la mascota.");
                return "redirect:/cliente";
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error interno: " + e.getMessage());
            return "redirect:/cliente";
        }
    }






}
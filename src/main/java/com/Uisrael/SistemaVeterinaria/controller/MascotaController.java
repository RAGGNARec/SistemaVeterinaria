package com.Uisrael.SistemaVeterinaria.controller;

import com.Uisrael.SistemaVeterinaria.entities.Cliente;
import com.Uisrael.SistemaVeterinaria.entities.Mascota;
import com.Uisrael.SistemaVeterinaria.repositories.ClienteRepository;
import com.Uisrael.SistemaVeterinaria.repositories.MascotaRepository;
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
public class MascotaController {

    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private ClienteRepository clienteRepository;


    // Listar todas las mascotas (activos e inactivos)
    @GetMapping("/Mascota")
    public String listarMascotas(Model model) {
        List<Mascota> mascota = mascotaRepository.findAll();  // Obtener toda las mascotas
        model.addAttribute("mascota", mascota);
        return "Mascota";  // Nombre de la vista HTML (Mascota.html)
    }

    // Mostrar formulario para agregar un nuevo mascota
    @GetMapping("/Mascota/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("mascota", new Mascota());
        model.addAttribute("clientes", clienteRepository.findAll()); // Cargar todos los clientes
        model.addAttribute("pageTitle", "Ingresar nueva mascota");
        return "FormMascota"; // Nombre de la vista
    }

    // Guardar nuevo mascota
    @PostMapping("/Mascota/save")
    public String guardarMascota(@ModelAttribute Mascota mascota, RedirectAttributes redirectAttributes) {
        try {
            mascotaRepository.save(mascota);  // Guardar la mascota (nuevo o editado)
            redirectAttributes.addFlashAttribute("message", "La mascota ha sido guardada con éxito");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Error al guardar la mascota: " + e.getMessage());
        }
        return "redirect:/Mascota"; // Redirigir a la lista de mascotas
    }

    // Editar un mascota
    @GetMapping("/Mascota/editar/{id}")
    public String editarMascota(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Mascota mascota = mascotaRepository.findById(id)
                    .orElseThrow(() -> new Exception("Mascota no encontrada"));  // Manejo de excepciones

            // Agregar la lista de clientes para la selección en el formulario
            List<Cliente> clientes = clienteRepository.findAll();
            model.addAttribute("clientes", clientes);
            model.addAttribute("mascota", mascota);
            model.addAttribute("pageTitle", "Editar mascota");

            return "FormMascota"; // Devuelve la vista para editar mascota
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/Mascota"; // Redirige si hay un error
        }
    }

    // Cambio de estado del mascota (activar/desactivar)
    @GetMapping("/Mascota/cambiarEstado/{id}")
    public String cambiarEstadoMascota(@PathVariable Long id) {
        Mascota mascota = mascotaRepository.findById(id).orElse(null);
        if (mascota != null) {
            mascota.setEstado(mascota.getEstado() == Mascota.EstadoMascota.ACTIVO ? Mascota.EstadoMascota.INACTIVO : Mascota.EstadoMascota.ACTIVO);
            mascotaRepository.save(mascota);  // Guardar cambios
        }
        return "redirect:/Mascota"; // Redirigir a la lista de ciente
    }

    /* Generar reporte
    @GetMapping("/reporte")
    public String generarReporte(Model model) {
        // Implementar la lógica para generar el reporte
        return "reporte";  // Vista para mostrar el reporte
    }*/
}


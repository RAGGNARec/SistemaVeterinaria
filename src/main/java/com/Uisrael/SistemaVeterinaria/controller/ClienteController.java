package com.Uisrael.SistemaVeterinaria.controller;

import com.Uisrael.SistemaVeterinaria.entities.Cliente;
import com.Uisrael.SistemaVeterinaria.entities.Cliente.EstadoCliente;
import com.Uisrael.SistemaVeterinaria.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    // Listar todos los ciente (activos e inactivos)
    @GetMapping("/Cliente")
    public String listarClientes(Model model) {
        List<Cliente> cliente = clienteRepository.findAll();  // Obtener todos los ciente
        model.addAttribute("cliente", cliente);
        return "Cliente";  // Nombre de la vista HTML (Cliente.html)
    }

    // Mostrar formulario para agregar un nuevo cliente
    @GetMapping("/Cliente/nuevo")
    public String mostrarFormulario(Model model) {
        Cliente cliente = new Cliente();
        model.addAttribute("cliente", cliente);
        model.addAttribute("pageTitle", "Ingresar nuevo cliente");
        return "FormCliente";
    }

    // Guardar nuevo cliente
    @PostMapping("/Cliente/save")
    public String guardarCliente(Cliente cliente, RedirectAttributes redirectAttributes) {
        try {
            clienteRepository.save(cliente);  // Guardar el producto (nuevo o editado)
            redirectAttributes.addFlashAttribute("message", "El cliente ha sido guardado con éxito");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Error al guardar el cliente: " + e.getMessage());
        }
        return "redirect:/Cliente";
    }

    // Editar un cliente
    @GetMapping("/Cliente/editar/{id}")
    public String editarCliente(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Cliente cliente = clienteRepository.findById(id)
                    .orElseThrow(() -> new Exception("Cliente no encontrado"));  // Manejo de excepciones
            model.addAttribute("cliente", cliente);
            model.addAttribute("pageTitle", "Editar cliente");
            return "FormCliente";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/Cliente";
        }
    }


    // Cambio de estado del cliente (activar/desactivar)
    @GetMapping("/Cliente/cambiarEstado/{id}")
    public String cambiarEstadoCliente(@PathVariable Long id) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        if (cliente != null) {
            cliente.setEstado(cliente.getEstado() == EstadoCliente.ACTIVO ? EstadoCliente.INACTIVO : EstadoCliente.ACTIVO);
            clienteRepository.save(cliente);  // Guardar cambios
        }
        return "redirect:/Cliente"; // Redirigir a la lista de ciente
    }

    /* Generar reporte
    @GetMapping("/reporte")
    public String generarReporte(Model model) {
        // Implementar la lógica para generar el reporte
        return "reporte";  // Vista para mostrar el reporte
    }*/
}

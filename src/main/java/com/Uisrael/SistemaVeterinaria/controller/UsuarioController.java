package com.Uisrael.SistemaVeterinaria.controller;

import com.Uisrael.SistemaVeterinaria.entities.Cita;
import com.Uisrael.SistemaVeterinaria.entities.Usuario;
import com.Uisrael.SistemaVeterinaria.entities.Rol;
import com.Uisrael.SistemaVeterinaria.service.CitaService;
import com.Uisrael.SistemaVeterinaria.service.RolService;
import com.Uisrael.SistemaVeterinaria.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolService rolService;
    @Autowired
    private CitaService citaService;

    // Listar usuarios
    @GetMapping("/usuario")
    public String listarUsuarios(Model model) {
        try{
            List<Usuario> usuarios = usuarioService.obtenerTodos(); // Obtener lista de usuarios
            List<Rol> roles = rolService.obtenerTodos();
            List<Cita> citasHoy = citaService.obtenerCitasDeHoy();
            model.addAttribute("citasHoy", citasHoy);

            model.addAttribute("roles", roles);
            model.addAttribute("listarUsuarios", usuarios);
            model.addAttribute("usuario", new Usuario());
        } catch (Exception e) {
            model.addAttribute("error", "Error al obtener las servicios: " + e.getMessage());
        }

        return "ModuloUsuario/usuario"; // Vista con tabla de usuarios
    }

    // Guardar nuevo usuario
    @PostMapping("/usuario/guardar")
    public String guardarNuevaUsuario(@ModelAttribute Usuario usuario,
                                      RedirectAttributes redirectAttributes){
        try {
            usuarioService.crear(usuario);
            redirectAttributes.addFlashAttribute("message", "El usuario ha sido registrado con éxito");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Error al guardar el usuario: " + e.getMessage());
        }
        return "redirect:/usuario";
    }


    // Editar un usuario
    @GetMapping("/usuario/editar/{id}")
    public String EditarUsuario(@PathVariable Long id, Model model) {
        // Obtener el usuario por su ID
        Usuario usuario = usuarioService.obtenerPorId(id);

        // Verificar si el historial clinico existe
        if (usuario == null) {
            model.addAttribute("error", "Usuario no encontrada.");
            return "redirect:/usuario"; // Redirigir a la lista de servicios
        }
        // Agregar los datos al modelo
        List<Rol> roles = rolService.obtenerTodos();

        model.addAttribute("roles", roles);
        model.addAttribute("usuario", usuario);
        return "ModuloUsuario/formEditar"; // Retorna la vista con el formulario de edición
    }


    // Cambio de estado del usuario (activar/desactivar)
    @GetMapping("/usuario/cambiarEstado/{id}")
    public String cambiarEstadoUsuario(@PathVariable Long id) {
        // Llamar al servicio para cambiar el estado
        usuarioService.cambiarEstado(id);
        return "redirect:/usuario"; // Redirigir a la lista de ciente
    }


    @PostMapping("/usuario/actualizar")
    public String actualizarUsuario(@ModelAttribute Usuario usuario,
                                    @RequestParam("roles") List<Long> rolesIds,
                                    RedirectAttributes redirectAttributes) {
        try {
            // Obtener los roles seleccionados por sus IDs
            List<Rol> roles = rolService.obtenerPorIds(rolesIds);

            // Actualizar el usuario con los roles
            usuarioService.actualizar(usuario.getId(), usuario, roles);

            redirectAttributes.addFlashAttribute("message", "Usuario actualizado con éxito.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar el usuario: " + e.getMessage());
        }
        return "redirect:/usuario";
    }



}
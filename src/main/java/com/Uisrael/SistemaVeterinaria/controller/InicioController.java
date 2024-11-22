package com.Uisrael.SistemaVeterinaria.controller;

import com.Uisrael.SistemaVeterinaria.entities.*;
import com.Uisrael.SistemaVeterinaria.repositories.*;
import com.Uisrael.SistemaVeterinaria.service.CitaService;
import com.Uisrael.SistemaVeterinaria.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class InicioController {

    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CitaService citaService;

    @Autowired
    private HistorialClinicoRepository historialClinicoRepository;

    @GetMapping("/admin")
    public String home(Model model, Authentication authentication) {
        // Obtener el nombre de usuario del usuario autenticado
        String username = authentication.getName();

        // Buscar el usuario en la base de datos y pasar sus datos al modelo
        Usuario usuario = usuarioService.findByUsername(username);
        if (usuario != null) {
            String nombreCompleto = usuario.getNombre() + " " + usuario.getApellido();
            model.addAttribute("username", nombreCompleto);
        }

        // Obtener estadísticas
        long totalMascotasActivas = mascotaRepository.findByEstado(Mascota.EstadoMascota.ACTIVO).size();
        long totalUsuariosActivas = usuarioRepository.findByEstado(Usuario.EstadoUsuario.ACTIVO).size();
        long totalHistorialClinicoActivas = historialClinicoRepository.findByEstado(HistorialClinico.EstadoHistorial.ACTIVO).size();
        long totalCitasPendientes = citaRepository.findByEstado(Cita.EstadoCita.PENDIENTE).size();
        long totalCitasCompletadas = citaRepository.findByEstado(Cita.EstadoCita.COMPLETADA).size();
        long totalCitasCanceladas = citaRepository.findByEstado(Cita.EstadoCita.CANCELADA).size();
        long totalCitas = citaRepository.count();
        long totalServiciosActivos = servicioRepository.findByEstado(Servicio.EstadoServicio.ACTIVO).size();

        // Agregar estadísticas al modelo
        model.addAttribute("totalHistorialClinico", totalHistorialClinicoActivas);
        model.addAttribute("totalMascotas", totalMascotasActivas);
        model.addAttribute("totalUsuarios", totalUsuariosActivas);
        model.addAttribute("totalCitasPendientes", totalCitasPendientes);
        model.addAttribute("totalCitasCompletadas", totalCitasCompletadas);
        model.addAttribute("totalCitasCanceladas", totalCitasCanceladas);
        model.addAttribute("totalCitas", totalCitas);
        model.addAttribute("totalServicios", totalServiciosActivos);

        // Obtener las citas de hoy
        List<Cita> citasHoy = citaService.obtenerCitasDeHoy();
        model.addAttribute("citasHoy", citasHoy);
        return "admin"; // Esto devolvería la vista 'admin.html'
    }

}

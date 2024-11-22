package com.Uisrael.SistemaVeterinaria.service.impl;

import com.Uisrael.SistemaVeterinaria.dto.CitaDTO;
import com.Uisrael.SistemaVeterinaria.entities.Cita;
import com.Uisrael.SistemaVeterinaria.entities.HistorialClinico;
import com.Uisrael.SistemaVeterinaria.entities.Mascota;
import com.Uisrael.SistemaVeterinaria.repositories.CitaRepository;
import com.Uisrael.SistemaVeterinaria.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CitaServiceImpl implements CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Override
    public List<CitaDTO> obtenerTodasLasCitas() {
        List<Cita> citas = citaRepository.findAll();
        return citas.stream()
                .map(cita -> new CitaDTO(
                        cita.getId(),
                        cita.getFecha(),
                        cita.getHora(),
                        cita.getMotivo(),
                        cita.getEstado().name()
                ))
                .collect(Collectors.toList());
    }


    @Override
    public List<Cita> obtenerTodos() {
        return citaRepository.findAll();
    }

    @Override
    public Cita obtenerPorId(Long id) {
        return citaRepository.findById(id).orElse(null);
    }

    @Override
    public Cita crear(Cita cita) {
        return citaRepository.save(cita);
    }

    @Override
    public Cita actualizar(Long id, Cita cita) {
        Cita citadb = citaRepository.findById(id).orElse(null);
        if(citadb != null) {
            citadb.setMotivo(cita.getMotivo());
            citadb.setFecha(cita.getFecha());
            citadb.setHora(cita.getHora());
            citadb.setMascota(cita.getMascota());
            citadb.setEstado(cita.getEstado());

            return citaRepository.save(citadb);
        }
        return null;
    }


    @Override
    public void cambiarEstado(Long id) {
        Cita cita = obtenerPorId(id);
        if (cita != null) {
            // Cambiar el estado de la cita entre PENDIENTE y COMPLETADA
            switch (cita.getEstado()) {
                case PENDIENTE:
                    cita.setEstado(Cita.EstadoCita.COMPLETADA);
                    break;
                case COMPLETADA:
                    cita.setEstado(Cita.EstadoCita.PENDIENTE);
                    break;
                case CANCELADA:
                    // No se permite cambiar el estado si está cancelada
                    throw new IllegalStateException("No se puede cambiar el estado de una cita cancelada.");
                default:
                    throw new IllegalStateException("Estado de cita desconocido: " + cita.getEstado());
            }
            citaRepository.save(cita);
        }
    }

    @Override
    public void cancelarCita(Long id) {
        Cita cita = obtenerPorId(id);
        if (cita != null) {
            // Cambiar el estado de la cita a CANCELADA
            cita.setEstado(Cita.EstadoCita.CANCELADA);
            citaRepository.save(cita);
        }
    }

    @Override
    public List<Cita> obtenerPorMascota(Mascota mascota) {
        return citaRepository.findByMascota(mascota);
    }


    @Override
    public List<Cita> obtenerCitasDeHoy() {
        LocalDate fechaHoy = LocalDate.now();
        return citaRepository.findByFecha(fechaHoy);
    }
}

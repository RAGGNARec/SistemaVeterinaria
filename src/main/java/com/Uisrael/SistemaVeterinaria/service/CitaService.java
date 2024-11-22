package com.Uisrael.SistemaVeterinaria.service;

import com.Uisrael.SistemaVeterinaria.dto.CitaDTO;
import com.Uisrael.SistemaVeterinaria.entities.Cita;
import com.Uisrael.SistemaVeterinaria.entities.HistorialClinico;
import com.Uisrael.SistemaVeterinaria.entities.Mascota;

import java.time.LocalDate;
import java.util.List;

public interface CitaService {
    List<CitaDTO> obtenerTodasLasCitas();
    List<Cita> obtenerTodos();
    Cita obtenerPorId(Long id);
    Cita crear(Cita cita);
    Cita actualizar(Long id, Cita cita);
    void cambiarEstado(Long id);
    void cancelarCita(Long id);


    List<Cita> obtenerPorMascota(Mascota mascota);

    List<Cita> obtenerCitasDeHoy(); // Nuevo metodo

}

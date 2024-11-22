package com.Uisrael.SistemaVeterinaria.service;

import com.Uisrael.SistemaVeterinaria.entities.HistorialVacunacion;
import com.Uisrael.SistemaVeterinaria.entities.HistorialVacunacion;
import com.Uisrael.SistemaVeterinaria.entities.Mascota;

import java.util.List;

public interface HistorialVacunacionService {
    List<HistorialVacunacion> obtenerTodos();
    HistorialVacunacion obtenerPorId(Long id);
    HistorialVacunacion crear(HistorialVacunacion historialVacunacion);
    HistorialVacunacion actualizar(Long id, HistorialVacunacion historialVacunacion);
    Long contar();

    List<HistorialVacunacion> obtenerPorMascota(Mascota mascota);

}

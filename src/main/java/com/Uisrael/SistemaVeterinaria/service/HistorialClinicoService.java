package com.Uisrael.SistemaVeterinaria.service;

import com.Uisrael.SistemaVeterinaria.entities.HistorialClinico;
import com.Uisrael.SistemaVeterinaria.entities.Mascota;

import java.util.List;

public interface HistorialClinicoService {
    List<HistorialClinico> obtenerTodos();
    HistorialClinico obtenerPorId(Long id);
    HistorialClinico crear(HistorialClinico historialClinico);
    HistorialClinico actualizar(Long id, HistorialClinico historialClinico);
    Long contar();
    void cambiarEstado(Long id);


    List<HistorialClinico> obtenerPorMascota(Mascota mascota);

}

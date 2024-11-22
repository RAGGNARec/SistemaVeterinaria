package com.Uisrael.SistemaVeterinaria.service;

import com.Uisrael.SistemaVeterinaria.entities.Servicio;

import java.util.List;

public interface ServicioService {
    List<Servicio> obtenerTodos();
    Servicio obtenerPorId(Long id);
    Servicio crear(Servicio servicio);
    Servicio actualizar(Long id, Servicio servicio);
    Long contar();
}


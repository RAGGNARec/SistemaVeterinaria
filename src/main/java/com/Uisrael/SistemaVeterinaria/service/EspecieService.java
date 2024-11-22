package com.Uisrael.SistemaVeterinaria.service;

import com.Uisrael.SistemaVeterinaria.entities.Especie;

import java.util.List;

public interface EspecieService {
    List<Especie> obtenerTodos();
    Especie obtenerPorId(Long id);
    Especie crear(Especie especie);
    Especie actualizar(Long id, Especie especie);
    Long contar();
}

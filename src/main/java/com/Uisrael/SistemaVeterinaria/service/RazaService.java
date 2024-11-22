package com.Uisrael.SistemaVeterinaria.service;

import com.Uisrael.SistemaVeterinaria.entities.Raza;

import java.util.List;

public interface RazaService {
    List<Raza> obtenerTodos();
    Raza obtenerPorId(Long id);
    Raza crear(Raza raza);
    Raza actualizar(Long id, Raza raza);
    Long contar();
    // Filtra por el ID de la especie
    List<Raza> obtenerPorEspecie(Long especieId);

}

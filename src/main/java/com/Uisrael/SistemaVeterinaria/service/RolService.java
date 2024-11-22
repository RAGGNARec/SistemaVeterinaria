package com.Uisrael.SistemaVeterinaria.service;

import com.Uisrael.SistemaVeterinaria.entities.Rol;

import java.util.List;

public interface RolService {
    List<Rol> obtenerTodos();
    List<Rol> obtenerPorIds(List<Long> ids);

}

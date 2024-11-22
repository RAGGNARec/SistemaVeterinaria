package com.Uisrael.SistemaVeterinaria.service.impl;

import com.Uisrael.SistemaVeterinaria.entities.Rol;
import com.Uisrael.SistemaVeterinaria.repositories.RolRepository;
import com.Uisrael.SistemaVeterinaria.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private RolRepository rolRepository; // Inyección del repositorio

    @Override
    public List<Rol> obtenerTodos() {
        return rolRepository.findAll(); // Obtener todos los roles desde la base de datos
    }

    @Override
    public List<Rol> obtenerPorIds(List<Long> ids) {
        return rolRepository.findAllById(ids);
    }
}

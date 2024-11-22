package com.Uisrael.SistemaVeterinaria.service.impl;

import com.Uisrael.SistemaVeterinaria.entities.Especie;
import com.Uisrael.SistemaVeterinaria.repositories.EspecieRepository;
import com.Uisrael.SistemaVeterinaria.service.EspecieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspecieServiceImpl implements EspecieService {

    @Autowired
    private EspecieRepository especieRepository;

    @Override
    public List<Especie> obtenerTodos() {
        return especieRepository.findAll();
    }

    @Override
    public Especie obtenerPorId(Long id) {
        return especieRepository.findById(id).orElse(null);
    }

    @Override
    public Especie crear(Especie especie) {
        return especieRepository.save(especie); // Guarda la especie
    }

    @Override
    public Especie actualizar(Long id, Especie especie) {
        Especie especiedb = especieRepository.findById(id).orElse(null);
        if(especiedb != null) {
            especiedb.setNombre(especie.getNombre());
            return especieRepository.save(especiedb);
        }
        return null;
    }

    @Override
    public Long contar() {
        return especieRepository.count();
    }
}
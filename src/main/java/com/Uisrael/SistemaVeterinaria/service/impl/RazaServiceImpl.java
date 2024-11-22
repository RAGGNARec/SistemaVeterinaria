package com.Uisrael.SistemaVeterinaria.service.impl;

import com.Uisrael.SistemaVeterinaria.entities.Raza;
import com.Uisrael.SistemaVeterinaria.repositories.RazaRepository;
import com.Uisrael.SistemaVeterinaria.service.RazaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RazaServiceImpl implements RazaService {

    @Autowired
    private RazaRepository razaRepository;

    @Override
    public List<Raza> obtenerTodos() {
        return razaRepository.findAll();
    }

    @Override
    public Raza obtenerPorId(Long id) {
        return razaRepository.findById(id).orElse(null);
    }

    @Override
    public Raza crear(Raza raza) {
        return razaRepository.save(raza); // Guarda la raza
    }

    @Override
    public Raza actualizar(Long id, Raza raza) {
        Raza razadb = razaRepository.findById(id).orElse(null);
        if (razadb != null) {
            razadb.setNombre(raza.getNombre());
            return razaRepository.save(razadb);
        }
        return null;
    }

    @Override
    public Long contar() {
        return razaRepository.count();
    }

    @Override
    public List<Raza> obtenerPorEspecie(Long especieId) {
        return razaRepository.findByEspecieId(especieId); // Llama al repositorio para filtrar
    }
}
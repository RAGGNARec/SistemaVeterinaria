package com.Uisrael.SistemaVeterinaria.service.impl;

import com.Uisrael.SistemaVeterinaria.entities.Servicio;
import com.Uisrael.SistemaVeterinaria.repositories.ServicioRepository;
import com.Uisrael.SistemaVeterinaria.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioServiceImpl implements ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;

    @Override
    public List<Servicio> obtenerTodos() {
        return servicioRepository.findAll();
    }

    @Override
    public Servicio obtenerPorId(Long id) {
        return servicioRepository.findById(id).orElse(null);
    }

    @Override
    public Servicio crear(Servicio servicio) {
        return servicioRepository.save(servicio); // Guarda la servicio
    }

    @Override
    public Servicio actualizar(Long id, Servicio servicio) {
        Servicio serviciodb = servicioRepository.findById(id).orElse(null);
        if(serviciodb != null) {
            serviciodb.setNombre(servicio.getNombre());
            serviciodb.setDescripcion(servicio.getDescripcion());
            serviciodb.setPrecio(servicio.getPrecio());

            return servicioRepository.save(serviciodb);
        }
        return null;
    }

    @Override
    public Long contar() {
        return servicioRepository.count();
    }
}
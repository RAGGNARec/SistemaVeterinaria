package com.Uisrael.SistemaVeterinaria.service.impl;

import com.Uisrael.SistemaVeterinaria.entities.HistorialVacunacion;
import com.Uisrael.SistemaVeterinaria.entities.Mascota;
import com.Uisrael.SistemaVeterinaria.repositories.HistorialVacunacionRepository;
import com.Uisrael.SistemaVeterinaria.service.HistorialVacunacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistorialVacunacionImpl implements HistorialVacunacionService {

    @Autowired
    private HistorialVacunacionRepository historialVacunacionRepository;

    @Override
    public List<HistorialVacunacion> obtenerTodos() {
        return historialVacunacionRepository.findAll();
    }

    @Override
    public HistorialVacunacion obtenerPorId(Long id) {
        return historialVacunacionRepository.findById(id).orElse(null);
    }

    @Override
    public HistorialVacunacion crear(HistorialVacunacion historialVacunacion) {
        return historialVacunacionRepository.save(historialVacunacion); // Guarda la historialVacunacion
    }

    @Override
    public HistorialVacunacion actualizar(Long id, HistorialVacunacion historialVacunacion) {
        HistorialVacunacion historialVacunaciondb = historialVacunacionRepository.findById(id).orElse(null);
        if (historialVacunaciondb != null) {
            historialVacunaciondb.setMascota(historialVacunacion.getMascota());
            historialVacunaciondb.setVacuna(historialVacunacion.getVacuna());
            historialVacunaciondb.setFecha(historialVacunacion.getFecha());
            historialVacunaciondb.setObservaciones(historialVacunacion.getObservaciones());
            return historialVacunacionRepository.save(historialVacunaciondb);
        }
        return null;
    }

    @Override
    public Long contar() {
        return historialVacunacionRepository.count();
    }

    @Override
    public List<HistorialVacunacion> obtenerPorMascota(Mascota mascota) {
        return historialVacunacionRepository.findByMascota(mascota);
    }
}
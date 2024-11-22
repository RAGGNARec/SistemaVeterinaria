package com.Uisrael.SistemaVeterinaria.service.impl;

import com.Uisrael.SistemaVeterinaria.entities.HistorialClinico;
import com.Uisrael.SistemaVeterinaria.entities.Mascota;
import com.Uisrael.SistemaVeterinaria.repositories.HistorialClinicoRepository;
import com.Uisrael.SistemaVeterinaria.service.HistorialClinicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistorialClinicoImpl implements HistorialClinicoService {

    @Autowired
    private HistorialClinicoRepository historialClinicoRepository;

    @Override
    public List<HistorialClinico> obtenerTodos() {
        return historialClinicoRepository.findAll();
    }

    @Override
    public HistorialClinico obtenerPorId(Long id) {
        return historialClinicoRepository.findById(id).orElse(null);
    }

    @Override
    public HistorialClinico crear(HistorialClinico historialClinico) {
        return historialClinicoRepository.save(historialClinico); // Guarda la historialClinico
    }

    @Override
    public HistorialClinico actualizar(Long id, HistorialClinico historialClinico) {
        HistorialClinico historialClinicodb = historialClinicoRepository.findById(id).orElse(null);
        if (historialClinicodb != null) {
            historialClinicodb.setMascota(historialClinico.getMascota());
            historialClinicodb.setObservaciones(historialClinico.getObservaciones());
            historialClinicodb.setFecha(historialClinico.getFecha());
            historialClinicodb.setEstado(historialClinico.getEstado());
            return historialClinicoRepository.save(historialClinicodb);
        }
        return null;
    }

    @Override
    public void cambiarEstado(Long id) {
        HistorialClinico historialClinico = obtenerPorId(id);
        if (historialClinico != null) {
            // Cambiar el estado de la mascota
            historialClinico.setEstado(historialClinico.getEstado() == HistorialClinico.EstadoHistorial.ACTIVO ? HistorialClinico.EstadoHistorial.INACTIVO : HistorialClinico.EstadoHistorial.ACTIVO);
            historialClinicoRepository.save(historialClinico);
        }
    }

    @Override
    public Long contar() {
        return historialClinicoRepository.count();
    }

    @Override
    public List<HistorialClinico> obtenerPorMascota(Mascota mascota) {
        return historialClinicoRepository.findByMascota(mascota);
    }
}
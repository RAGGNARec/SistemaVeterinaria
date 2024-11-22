package com.Uisrael.SistemaVeterinaria.service.impl;

import com.Uisrael.SistemaVeterinaria.entities.Mascota;
import com.Uisrael.SistemaVeterinaria.entities.Usuario;
import com.Uisrael.SistemaVeterinaria.repositories.MascotaRepository;
import com.Uisrael.SistemaVeterinaria.service.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MascotaServiceImpl implements MascotaService {
    @Autowired
    private MascotaRepository mascotaRepository;


    @Override
    public List<Mascota> obtenerTodos() {
        return mascotaRepository.findAll();
    }

    @Override
    public Mascota obtenerPorId(Long id) {
        return mascotaRepository.findById(id).orElse(null);
    }

    @Override
    public Mascota crear(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    @Override
    public Mascota actualizar(Long id, Mascota mascota) {
        Mascota mascotadb = mascotaRepository.findById(id).orElse(null);
        if(mascotadb != null) {
            mascotadb.setNombre(mascota.getNombre());
            mascotadb.setSexo(mascota.getSexo());
            mascotadb.setFechaNacimiento(mascota.getFechaNacimiento());
            mascotadb.setEdad(mascota.getEdad());
            mascotadb.setColor(mascota.getColor());
            mascotadb.setPeso(mascota.getPeso());
            mascotadb.setEstado(mascota.getEstado());

            return mascotaRepository.save(mascotadb);
        }
        return null;
    }

    @Override
    public void cambiarEstado(Long id) {
        Mascota mascota = obtenerPorId(id);
        if (mascota != null) {
            // Cambiar el estado de la mascota
            mascota.setEstado(mascota.getEstado() == Mascota.EstadoMascota.ACTIVO ? Mascota.EstadoMascota.INACTIVO : Mascota.EstadoMascota.ACTIVO);
            mascotaRepository.save(mascota);
        }
    }

    @Override
    public Long contar() {
        return mascotaRepository.count();
    }


    @Override
    public List<Mascota> obtenerMascotasPorCliente(Usuario cliente) {
        return mascotaRepository.findByCliente(cliente); // Metodo que obtiene las mascotas por usuario
    }
}
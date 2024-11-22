package com.Uisrael.SistemaVeterinaria.service;

import com.Uisrael.SistemaVeterinaria.entities.Mascota;
import com.Uisrael.SistemaVeterinaria.entities.Usuario;

import java.util.List;

public interface MascotaService {
    List<Mascota> obtenerTodos();
     Mascota obtenerPorId(Long id);
    Mascota crear(Mascota mascota);
    Mascota actualizar(Long id, Mascota mascota);
    void cambiarEstado(Long id);
    Long contar();

    List<Mascota> obtenerMascotasPorCliente(Usuario cliente);

}

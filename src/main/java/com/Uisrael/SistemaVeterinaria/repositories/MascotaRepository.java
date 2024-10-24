package com.Uisrael.SistemaVeterinaria.repositories;

import com.Uisrael.SistemaVeterinaria.entities.Cliente;
import com.Uisrael.SistemaVeterinaria.entities.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {
    //Método para obtener solo MASCOTAS por estado (activo o inactivo)
    List<Mascota> findByEstado(Mascota.EstadoMascota estado);
}
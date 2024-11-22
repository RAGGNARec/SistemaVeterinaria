package com.Uisrael.SistemaVeterinaria.repositories;

import com.Uisrael.SistemaVeterinaria.entities.Cita;
import com.Uisrael.SistemaVeterinaria.entities.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findByFecha(LocalDate fecha); // Consulta por fecha
    //Metodo para obtener total de servicios ACTIVOS O INACTIVOS
    List<Cita> findByEstado(Cita.EstadoCita estado);

    List<Cita> findByMascota(Mascota mascota);

}
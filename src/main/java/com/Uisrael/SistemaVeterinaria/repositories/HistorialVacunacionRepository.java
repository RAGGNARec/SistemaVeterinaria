package com.Uisrael.SistemaVeterinaria.repositories;

import com.Uisrael.SistemaVeterinaria.entities.HistorialVacunacion;
import com.Uisrael.SistemaVeterinaria.entities.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistorialVacunacionRepository extends JpaRepository<HistorialVacunacion, Long> {
    List<HistorialVacunacion> findByMascota(Mascota mascota);

}

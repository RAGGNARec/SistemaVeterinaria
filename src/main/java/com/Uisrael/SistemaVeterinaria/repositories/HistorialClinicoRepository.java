package com.Uisrael.SistemaVeterinaria.repositories;

import com.Uisrael.SistemaVeterinaria.entities.HistorialClinico;
import com.Uisrael.SistemaVeterinaria.entities.HistorialClinico;
import com.Uisrael.SistemaVeterinaria.entities.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HistorialClinicoRepository extends JpaRepository<HistorialClinico, Long> {
    //Metodo para obtener total de mascotas ACTIVOS O INACTIVOS
    List<HistorialClinico> findByEstado(HistorialClinico.EstadoHistorial estado);

    List<HistorialClinico> findByMascota(Mascota mascota);

}
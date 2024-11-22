package com.Uisrael.SistemaVeterinaria.repositories;

import com.Uisrael.SistemaVeterinaria.entities.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long> {
    //Metodo para obtener total de servicios ACTIVOS O INACTIVOS
    List<Servicio> findByEstado(Servicio.EstadoServicio estado);

}

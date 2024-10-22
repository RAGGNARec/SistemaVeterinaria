package com.Uisrael.SistemaVeterinaria.repositories;

import com.Uisrael.SistemaVeterinaria.entities.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Integer> {
}
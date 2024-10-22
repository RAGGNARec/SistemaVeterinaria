package com.Uisrael.SistemaVeterinaria.repositories;

import com.Uisrael.SistemaVeterinaria.entities.HistorialClinico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistorialClinicoRepository extends JpaRepository<HistorialClinico, Integer> {
}
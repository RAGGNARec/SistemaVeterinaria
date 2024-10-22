package com.Uisrael.SistemaVeterinaria.repositories;

import com.Uisrael.SistemaVeterinaria.entities.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeterinarioRepository extends JpaRepository<Veterinario, Integer> {
}
package com.Uisrael.SistemaVeterinaria.repositories;

import com.Uisrael.SistemaVeterinaria.entities.Raza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface RazaRepository extends JpaRepository<Raza, Long> {
    // Filtra por el ID de la especie
    List<Raza> findByEspecieId(Long especieId);


}

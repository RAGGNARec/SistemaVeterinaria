package com.Uisrael.SistemaVeterinaria.repositories;

import com.Uisrael.SistemaVeterinaria.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
package com.Uisrael.SistemaVeterinaria.repositories;

import com.Uisrael.SistemaVeterinaria.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
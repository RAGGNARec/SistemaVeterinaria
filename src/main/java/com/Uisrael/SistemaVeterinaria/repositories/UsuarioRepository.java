package com.Uisrael.SistemaVeterinaria.repositories;

import com.Uisrael.SistemaVeterinaria.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Declaración del metodo para buscar por username
    Usuario findByUsername(String username);

    //Metodo para obtener total de mascotas ACTIVOS O INACTIVOS
    List<Usuario> findByEstado(Usuario.EstadoUsuario estado);


}
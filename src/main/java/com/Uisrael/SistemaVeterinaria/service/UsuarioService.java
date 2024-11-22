package com.Uisrael.SistemaVeterinaria.service;

import com.Uisrael.SistemaVeterinaria.entities.Rol;
import com.Uisrael.SistemaVeterinaria.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UsuarioService {

    // Busca un usuario por su nombre de usuario (username) en la base de datos
    Usuario findByUsername(String username);

    // Obtiene todos los usuarios de la base de datos
    List<Usuario> obtenerTodos();

    // Obtiene un usuario por su ID
    Usuario obtenerPorId(Long id);

    // Crea un nuevo usuario con su imagen de perfil
    Usuario crear(Usuario usuario) ;

    // Actualiza la información de un usuario existente, incluyendo su imagen de perfil y roles
    Usuario actualizar(Long id, Usuario usuario, List<Rol> roles) ;

    void cambiarEstado(Long id);

}
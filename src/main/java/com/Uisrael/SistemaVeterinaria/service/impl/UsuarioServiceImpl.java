package com.Uisrael.SistemaVeterinaria.service.impl;

import com.Uisrael.SistemaVeterinaria.entities.Usuario;
import com.Uisrael.SistemaVeterinaria.entities.Rol;
import com.Uisrael.SistemaVeterinaria.entities.Usuario;
import com.Uisrael.SistemaVeterinaria.repositories.UsuarioRepository;
import com.Uisrael.SistemaVeterinaria.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Busca un usuario por su nombre de usuario (username)
    @Override
    public Usuario findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    // Obtiene todos los usuarios registrados en la base de datos
    @Override
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    // Obtiene un usuario por su ID
    @Override
    public Usuario obtenerPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    // Crea un nuevo usuario con su imagen de perfil, encriptando la contraseña
    @Override
    public Usuario crear( Usuario usuario)  {

        // Encripta la contraseña antes de guardarla
        String contraseñaEncriptada = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(contraseñaEncriptada);

        // Guarda el usuario en la base de datos
        return usuarioRepository.save(usuario);
    }

    // Actualiza la información de un usuario, incluyendo su imagen de perfil y roles
    @Override
    public Usuario actualizar(Long id, Usuario usuario, List<Rol> roles){

        Usuario usuariodb = usuarioRepository.findById(id).orElse(null);


        if(usuariodb != null) {
            // Actualiza los campos del usuario con la nueva información
            usuariodb.setNombre(usuario.getNombre());
            usuariodb.setApellido(usuario.getApellido());
            usuariodb.setEmail(usuario.getEmail());
            usuariodb.setUsername(usuario.getUsername());
            usuariodb.setCedula(usuario.getCedula());
            usuariodb.setDireccion(usuario.getDireccion());

            // Actualiza los roles del usuario
            usuariodb.setRoles(roles);

            // Encripta la nueva contraseña si se proporciona una
            if (usuario.getPassword() != null && !usuario.getPassword().isEmpty()) {
                String contraseñaEncriptada = passwordEncoder.encode(usuario.getPassword());
                usuariodb.setPassword(contraseñaEncriptada);
            }
            // Guarda los cambios del usuario
            return usuarioRepository.save(usuariodb);
        }
        return null;
    }

    @Override
    public void cambiarEstado(Long id) {
        Usuario usuario = obtenerPorId(id);
        if (usuario != null) {
            // Cambiar el estado de la usuario
            usuario.setEstado(usuario.getEstado() == Usuario.EstadoUsuario.ACTIVO ? Usuario.EstadoUsuario.INACTIVO : Usuario.EstadoUsuario.ACTIVO);
            usuarioRepository.save(usuario);
        }
    }

}

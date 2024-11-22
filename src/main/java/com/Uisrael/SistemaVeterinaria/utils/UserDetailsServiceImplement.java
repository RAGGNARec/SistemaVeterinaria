package com.Uisrael.SistemaVeterinaria.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.Uisrael.SistemaVeterinaria.entities.Usuario;
import com.Uisrael.SistemaVeterinaria.repositories.UsuarioRepository;

public class UserDetailsServiceImplement implements UserDetailsService{

    @Autowired
    UsuarioRepository usuarioRepository;

    //Estamos leyendo un usurio de la base de datos

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByUsername(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("No se encontro el usuario");
        }
        //Aqui estoy configurando los roles obtenidos en UserDETAILSsERVICE CON el usuario
        UserDetailsServices userDetailsServices = new UserDetailsServices(usuario);

        return userDetailsServices;
    }

}

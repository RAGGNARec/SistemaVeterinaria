package com.Uisrael.SistemaVeterinaria.utils;

import com.Uisrael.SistemaVeterinaria.entities.Rol;
import com.Uisrael.SistemaVeterinaria.entities.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailsServices implements UserDetails{

	private Usuario usuario;

	public UserDetailsServices(Usuario usuario) {

		this.usuario = usuario;

	}
	//este metodo solo para ver que nombres de los roles tengo y setearlos al usuario
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> roles = new ArrayList<>();

		if (usuario != null && usuario.getRoles() != null) {
			for (Rol rol : usuario.getRoles()) {
				// Convertir RolTipo a String usando .name() para que sea compatible
				String rolNombre = rol.getTipo().name(); // o usa .toString() si prefieres
				roles.add(new SimpleGrantedAuthority(rolNombre)); // Agrega el rol como SimpleGrantedAuthority
			}
		}

		return roles;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return usuario.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return usuario.getUsername();
	}

	// Método para obtener el nombre del usuario
	public String getNombre() {
		return usuario.getNombre();
	}

	/* Método para obtener el apellido del usuario
	public String getApellido() {
		return usuario.getApellido();
	}*/

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}

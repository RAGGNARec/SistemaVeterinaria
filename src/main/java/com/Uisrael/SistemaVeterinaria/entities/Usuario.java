package com.Uisrael.SistemaVeterinaria.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, length = 100)
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    @Pattern(regexp = ".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*", message = "La contraseña debe contener al menos un carácter especial")
    private String password;

    @Column(nullable = false, length = 100) // Nombre no nulo
    private String nombre;

    @Column(nullable = false, length = 100) // Apellido no nulo
    private String apellido;

    @Column(length = 255) // Dirección opcional
    private String direccion;

    @Column(length = 20) // Teléfono opcional
    private String telefono;

    @Column(nullable = false, unique = true, length = 100) // Email único y no nulo
    private String email;

    @Column(nullable = false, unique = true, length = 20) // Cédula única y no nulo
    private String cedula;

    private String fotoPerfil;

    @Enumerated(EnumType.STRING) // Usa el enum para el estado
    @Column(nullable = false)
    private EstadoUsuario estado = EstadoUsuario.ACTIVO; // Por defecto, ACTIVO

    public enum EstadoUsuario {
        ACTIVO, INACTIVO;
    }

    //RELACIONES DE TABLAS
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)

    @JoinTable(
            name = "usuario_rol",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_rol")
    )
    @ToString.Exclude
    private List<Rol> roles = new ArrayList<>();

    // Relación uno a muchos con Mascota (un usuario puede tener varias mascotas)
    @OneToMany(mappedBy = "cliente")
    private List<Mascota> mascotas = new ArrayList<>();
}



package com.Uisrael.SistemaVeterinaria.entities;

import jakarta.persistence.*;
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
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Usamos el enum directamente para el tipo de rol
    @Enumerated(EnumType.STRING) // Esto almacenará el nombre del enum como un String en la base de datos
    @Column(nullable = false, unique = true, length = 50)
    private RolTipo tipo;

    // Definimos el enum RolTipo
    public enum RolTipo {
        CLIENTE, VETERINARIO, ADMINISTRADOR;
    }

    // RELACION DE TABLA
    @ManyToMany(mappedBy = "roles")
    @ToString.Exclude
    private List<Usuario> usuarios = new ArrayList<>();

}
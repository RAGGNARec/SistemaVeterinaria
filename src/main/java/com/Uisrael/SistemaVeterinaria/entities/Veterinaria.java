package com.Uisrael.SistemaVeterinaria.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Veterinaria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 255)
    private String direccion;

    @Column(nullable = false, length = 20)
    private String telefono;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, unique = true, length = 20)
    private String cedula;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoVeterinaria estado = EstadoVeterinaria.ACTIVO;

    public enum EstadoVeterinaria {
        ACTIVO, INACTIVO
    }
}
package com.Uisrael.SistemaVeterinaria.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Veterinario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    private String especialidad;

    @Column(length = 20)
    private String telefono;

    @Column(length = 100)
    private String email;

    @Column(nullable = false, unique = true, length = 20)
    private String cedula;

    private String foto;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoVeterinario estado = EstadoVeterinario.ACTIVO;

    @OneToOne
    @JoinColumn(name = "usuario_id", foreignKey = @ForeignKey(name = "FK_veterinario_usuario_id"))
    private Usuario usuario;

    public enum EstadoVeterinario {
        ACTIVO, INACTIVO
    }
}
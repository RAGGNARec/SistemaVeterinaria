package com.Uisrael.SistemaVeterinaria.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String password;

    @ManyToOne
    @JoinColumn(name = "rol_id", foreignKey = @ForeignKey(name = "FK_usuario_rol_id"))
    private Rol rol;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoUsuario estado = EstadoUsuario.ACTIVO;

    public enum EstadoUsuario {
        ACTIVO, INACTIVO
    }
}
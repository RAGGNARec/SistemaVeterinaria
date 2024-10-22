package com.Uisrael.SistemaVeterinaria.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String especie;

    @Column(length = 50)
    private String raza;

    private String sexo;

    private Integer edad;

    @Column(length = 50)
    private String color;

    private Double peso;

    private String foto;

    private java.sql.Date fechaNacimiento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoMascota estado = EstadoMascota.ACTIVO;

    @ManyToOne
    @JoinColumn(name = "cliente_id", foreignKey = @ForeignKey(name = "FK_mascota_cliente_id"))
    private Cliente cliente;

    public enum EstadoMascota {
        ACTIVO, INACTIVO
    }
}
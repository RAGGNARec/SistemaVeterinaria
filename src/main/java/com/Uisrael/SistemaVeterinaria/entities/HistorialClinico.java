package com.Uisrael.SistemaVeterinaria.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class HistorialClinico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;

    private String observaciones;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoHistorial estado = EstadoHistorial.ACTIVO;

    @ManyToOne
    @JoinColumn(name = "mascota_id", foreignKey = @ForeignKey(name = "FK_historialClinico_mascota_id"))
    private Mascota mascota; // Se cambió el nombre de la FK

    public enum EstadoHistorial {
        ACTIVO, INACTIVO
    }
}
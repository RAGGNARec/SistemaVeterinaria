package com.Uisrael.SistemaVeterinaria.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;

    private LocalTime hora;

    private String motivo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoCita estado = EstadoCita.ACTIVO;

    @ManyToOne
    @JoinColumn(name = "mascota_id", foreignKey = @ForeignKey(name = "FK_cita_mascota_id"))
    private Mascota mascota;

    @ManyToOne
    @JoinColumn(name = "veterinario_id", foreignKey = @ForeignKey(name = "FK_cita_veterinario_id"))
    private Veterinario veterinario;

    public enum EstadoCita {
        ACTIVO, INACTIVO
    }
}
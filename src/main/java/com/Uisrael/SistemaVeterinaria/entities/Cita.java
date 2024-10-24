package com.Uisrael.SistemaVeterinaria.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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
    private EstadoCita estado = EstadoCita.PENDIENTE; // Por defecto, PENDIENTE

    @ManyToOne
    @JoinColumn(name = "mascota_id", foreignKey = @ForeignKey(name = "FK_cita_mascota_id"))
    private Mascota mascota;

    @ManyToOne
    @JoinColumn(name = "veterinario_id", foreignKey = @ForeignKey(name = "FK_cita_veterinario_id"))
    private Veterinario veterinario;

    @OneToMany(mappedBy = "cita", cascade = CascadeType.ALL)
    private List<CitaServicio> citaServicios;  // Relación con la tabla intermedia CitaServicio

    public enum EstadoCita {
        PENDIENTE, COMPLETADA, CANCELADA
    }
}

package com.Uisrael.SistemaVeterinaria.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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

    @DateTimeFormat(pattern = "yyyy-MM-dd") // Formato de fecha
    private LocalDate fecha;

    @DateTimeFormat(pattern = "HH:mm") // Formato de hora
    private LocalTime hora;

    private String motivo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoCita estado = EstadoCita.PENDIENTE; // Por defecto, PENDIENTE

    public enum EstadoCita {
        PENDIENTE, COMPLETADA, CANCELADA;
    }

    //RELACIONES
    @ManyToOne
    @JoinColumn(name = "mascota_id", foreignKey = @ForeignKey(name = "FK_cita_mascota_id"))
    private Mascota mascota;

    // Relación ManyToOne con Servicio
    @ManyToOne
    @JoinColumn(name = "servicio_id", foreignKey = @ForeignKey(name = "FK_cita_servicio_id"))
    private Servicio servicio;  // Relación muchos a uno con Servicio
}

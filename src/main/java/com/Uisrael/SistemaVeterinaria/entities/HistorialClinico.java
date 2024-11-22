package com.Uisrael.SistemaVeterinaria.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "historial_clinico")
public class HistorialClinico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING) // Usa el enum para el estado
    @Column(nullable = false)
    private EstadoHistorial estado = EstadoHistorial.ACTIVO; // Por defecto, ACTIVO

    public enum EstadoHistorial {
        ACTIVO, INACTIVO;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd") // Formato de fecha
    @Column(nullable = false) // Fecha no nula
    private LocalDate fecha;

    @Column(length = 255) // Observaciones opcionales
    private String observaciones;

    @ManyToOne // Relación con la tabla Mascota
    @JoinColumn(name = "mascota_id", foreignKey = @ForeignKey(name = "FK_historial_clinico_mascota_id"), nullable = false)
    private Mascota mascota; // ID de la mascota


}

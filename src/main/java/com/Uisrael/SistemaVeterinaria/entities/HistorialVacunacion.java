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
public class HistorialVacunacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // Relación con la tabla Mascota
    @JoinColumn(name = "mascota_id", foreignKey = @ForeignKey(name = "FK_historial_vacunacion_mascota_id"), nullable = false)
    private Mascota mascota; // ID de la mascota

    @DateTimeFormat(pattern = "yyyy-MM-dd") // Formato de fecha
    @Column(nullable = false) // Fecha no nula
    private LocalDate fecha;

    @Column(nullable = false, length = 100) // Vacuna no nula
    private String vacuna;

    @Column(columnDefinition = "TEXT") // Observaciones opcionales
    private String observaciones;
}

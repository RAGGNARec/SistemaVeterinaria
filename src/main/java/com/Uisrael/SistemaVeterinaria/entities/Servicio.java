package com.Uisrael.SistemaVeterinaria.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) // No puede ser Nula
    private String nombre;  // Nombre del servicio

    @Column(nullable = false, length = 500) // Campo de descripción
    private String descripcion;  // Descripción del servicio

    @Column(nullable = false) // No puede ser Nula
    private Double precio;  // Precio del servicio

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoServicio estado = EstadoServicio.ACTIVO;  // Estado del servicio (por defecto es ACTIVO)

    public enum EstadoServicio {
        ACTIVO, INACTIVO; // Estados posibles
    }

    // Relación OneToMany con Cita
    @OneToMany(mappedBy = "servicio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cita> citas;  // Relación uno a muchos con Cita
}

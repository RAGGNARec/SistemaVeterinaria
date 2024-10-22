package com.Uisrael.SistemaVeterinaria.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) //No puede ser Nula
    private String nombre;  // Nombre del servicio

    @Column(nullable = false) //No puede ser Nula
    private Double precio;  // Precio del servicio

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoServicio estado = EstadoServicio.ACTIVO;  // Estado del servicio (ACTIVO, INACTIVO)

    public enum EstadoServicio {
        ACTIVO, INACTIVO
    }
}

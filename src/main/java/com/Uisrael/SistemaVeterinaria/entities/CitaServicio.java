package com.Uisrael.SistemaVeterinaria.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CitaServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cita_id", foreignKey = @ForeignKey(name = "FK_citaservicio_cita_id"))
    private Cita cita;

    @ManyToOne
    @JoinColumn(name = "servicio_id", foreignKey = @ForeignKey(name = "FK_citaservicio_servicio_id"))
    private Servicio servicio;

    private Integer cantidad;  // Cantidad del servicio prestado
}

package com.Uisrael.SistemaVeterinaria.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50) // Color no nulo
    private String color;

    @Column(nullable = false) // Edad no nula
    private int edad;

    @DateTimeFormat(pattern = "yyyy-MM-dd") // Formato de fecha
    @Column(name = "fecha_nacimiento") // Nombre de la columna en la tabla
    private LocalDate fechaNacimiento;

    @Column(length = 255) // Foto opcional
    private String foto;

    @Column(nullable = false, length = 100) // Nombre no nulo
    private String nombre;

    @Column // Peso opcional
    private Double peso;

    @Column(nullable = false, length = 10) // Sexo no nulo
    private String sexo; // Cambiado de ENUM a VARCHAR

    public enum Sexo {
        MACHO, HEMBRA;
    }

    @Enumerated(EnumType.STRING) // Usa el enum para el estado
    @Column(nullable = false)
    private EstadoMascota estado = EstadoMascota.ACTIVO; // Por defecto, ACTIVO

    public enum EstadoMascota {
        ACTIVO, INACTIVO, TRATAMIENTO, FALLECIDO;
    }

    // Relación muchos a uno con Raza
    @ManyToOne
    @JoinColumn(name = "raza_id", foreignKey = @ForeignKey(name = "FK_mascota_raza_id"))
    private Raza raza;

    // Relación con la Cita
    @OneToMany(mappedBy = "mascota", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cita> citas;  // Relación uno a muchos con Cita

    // Relación con Usuario (Cliente)
    @ManyToOne
    @JoinColumn(name = "cliente_id", foreignKey = @ForeignKey(name = "FK_mascota_cliente_id"))
    private Usuario cliente; // Relación con Cliente (Usuario con rol CLIENTE)

}

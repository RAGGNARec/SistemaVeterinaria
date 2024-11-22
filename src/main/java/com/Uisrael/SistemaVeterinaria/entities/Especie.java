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
public class Especie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100) // Nombre único y no nulo
    private String nombre;

    // Relación uno a muchos con Raza
    @OneToMany(mappedBy = "especie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Raza> razas;  // Cada especie puede tener varias razas
}

package com.Uisrael.SistemaVeterinaria.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Raza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100) // Nombre único y no nulo
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "especie_id", foreignKey = @ForeignKey(name = "FK_raza_especie_id"))
    private Especie especie; // Relación con especie

    // Relación uno a muchos con Mascota
    @OneToMany(mappedBy = "raza", cascade = CascadeType.ALL)
    private List<Mascota> mascotas = new ArrayList<>();


}
package com.Uisrael.SistemaVeterinaria.repositories;

import com.Uisrael.SistemaVeterinaria.entities.Mascota;
import com.Uisrael.SistemaVeterinaria.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {

    Optional<Mascota> findById(Long id);

    //Metodo para obtener total de servicios ACTIVOS O INACTIVOS
    List<Mascota> findByEstado(Mascota.EstadoMascota estado);

    //Metodo para obtener las mascotas asociadas a un cliente (usuario)
    List<Mascota> findByCliente(Usuario cliente);

}
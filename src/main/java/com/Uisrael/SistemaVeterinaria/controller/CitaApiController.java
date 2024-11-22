package com.Uisrael.SistemaVeterinaria.controller;

import com.Uisrael.SistemaVeterinaria.dto.CitaDTO;
import com.Uisrael.SistemaVeterinaria.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class CitaApiController {

    @Autowired
    private CitaService citaService;

    @GetMapping
    public List<CitaDTO> obtenerTodasLasCitas() {
        return citaService.obtenerTodasLasCitas();
    }
}

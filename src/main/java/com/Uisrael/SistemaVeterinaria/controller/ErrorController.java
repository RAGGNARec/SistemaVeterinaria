package com.Uisrael.SistemaVeterinaria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {
	
	@GetMapping("/404")
	public String home() {
        return "404";
    }

}

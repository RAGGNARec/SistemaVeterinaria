package com.Uisrael.SistemaVeterinaria.config;

import com.Uisrael.SistemaVeterinaria.utils.LoginSuccessHandler;
import com.Uisrael.SistemaVeterinaria.utils.UserDetailsServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImplement();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private LoginSuccessHandler autheSuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(httpRequest-> //permite configura la restriccion a las vistas
                {
                    //RUTAS PARA LOS USUARIOS
                    httpRequest.requestMatchers(HttpMethod.GET,"/admin").hasAnyAuthority("ADMINISTRADOR");
                    httpRequest.requestMatchers(HttpMethod.GET,"/cliente").hasAnyAuthority("CLIENTE");
                    httpRequest.requestMatchers(HttpMethod.GET,"/veterinario").hasAnyAuthority("VETERINARIO");

                    //Configurar endpoints publicos
                    httpRequest.requestMatchers(HttpMethod.GET,"/calendario").hasAnyAuthority("ADMINISTRADOR");
                    httpRequest.requestMatchers(HttpMethod.GET,"/cita").hasAnyAuthority("ADMINISTRADOR");
                    httpRequest.requestMatchers(HttpMethod.GET,"/especie").hasAnyAuthority("ADMINISTRADOR");
                    httpRequest.requestMatchers(HttpMethod.GET,"/historialClinico").hasAnyAuthority("ADMINISTRADOR");
                    httpRequest.requestMatchers(HttpMethod.GET,"/historialVacunacion").hasAnyAuthority("ADMINISTRADOR");
                    httpRequest.requestMatchers(HttpMethod.GET,"/mascota").hasAnyAuthority("ADMINISTRADOR");
                    httpRequest.requestMatchers(HttpMethod.GET,"/raza").hasAnyAuthority("ADMINISTRADOR");
                    httpRequest.requestMatchers(HttpMethod.GET,"/servicio").hasAnyAuthority("ADMINISTRADOR");
                    httpRequest.requestMatchers(HttpMethod.GET,"/rol").hasAnyAuthority("ADMINISTRADOR");
                    httpRequest.requestMatchers(HttpMethod.GET,"/usuario").hasAnyAuthority("ADMINISTRADOR");
                    httpRequest.requestMatchers(HttpMethod.GET,"/veterinaria").hasAnyAuthority("ADMINISTRADOR");

                    //RUTAS RESOURCES TODOS LOS PERMISOS
                    httpRequest.requestMatchers(HttpMethod.GET,"/assets/**").permitAll();
                    httpRequest.requestMatchers(HttpMethod.GET,"/css/**").permitAll();
                    httpRequest.requestMatchers(HttpMethod.GET,"/datatables/**").permitAll();
                    httpRequest.requestMatchers(HttpMethod.GET,"/img/**").permitAll();
                    httpRequest.requestMatchers(HttpMethod.GET,"/js/**").permitAll();
                    httpRequest.requestMatchers(HttpMethod.GET,"/lib/**").permitAll();
                    httpRequest.requestMatchers(HttpMethod.GET,"/scss/**").permitAll();

                    httpRequest.anyRequest().authenticated();//anyrequest es cualquier otra ruta  no definida , siempre que este autentificado lo permite ingresar
                })
                .formLogin((form) -> form
                        .loginPage("/login")
                        .successHandler(autheSuccessHandler)
                        .permitAll()

                )
                .logout(logout -> logout
                        .permitAll()
                        .logoutSuccessUrl("/login?logout=true") // Redirige al login con el parámetro logout=true
                )
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedPage("/404")
                );
        return http.build();
    }
}

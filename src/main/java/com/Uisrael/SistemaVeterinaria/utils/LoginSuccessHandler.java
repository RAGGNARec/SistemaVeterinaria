package com.Uisrael.SistemaVeterinaria.utils;

import java.io.IOException;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        // Este metodo redirige a los usuarios autenticados a diferentes páginas según sus roles
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        if (roles.contains("ADMINISTRADOR")) {
            response.sendRedirect("/admin");
        } else if (roles.contains("VETERINARIO")) {
            response.sendRedirect("/veterinario");
        } else if (roles.contains("CLIENTE")) {
            response.sendRedirect("/cliente");
        }
    }
}

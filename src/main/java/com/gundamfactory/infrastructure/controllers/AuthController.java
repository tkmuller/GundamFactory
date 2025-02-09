package com.gundamfactory.infrastructure.controllers;

import com.gundamfactory.infrastructure.config.security.JwtUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        // Aquí deberías validar las credenciales contra una base de datos
        if ("admin".equals(username) && "password".equals(password)) {
            return jwtUtil.generateToken(username);
        } else {
            throw new RuntimeException("Credenciales inválidas");
        }
    }
}

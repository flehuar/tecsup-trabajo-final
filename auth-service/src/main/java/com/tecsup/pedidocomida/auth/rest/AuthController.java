package com.tecsup.pedidocomida.auth.rest;

import com.tecsup.pedidocomida.auth.security.JwtTokenService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final String STATIC_USERNAME = "admin";
    private static final String STATIC_PASSWORD = "miContrasen@";

    private final JwtTokenService tokenService;

    public AuthController(JwtTokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, String> login(@RequestBody LoginRequest request) {
        if (!STATIC_USERNAME.equals(request.username()) || !STATIC_PASSWORD.equals(request.password())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales invalidas");
        }

        String token = tokenService.generateToken(request.username(), "ADMIN");
        return Map.of(
                "token", token,
                "tokenType", "Bearer",
                "username", request.username(),
                "role", "ADMIN"
        );
    }
}

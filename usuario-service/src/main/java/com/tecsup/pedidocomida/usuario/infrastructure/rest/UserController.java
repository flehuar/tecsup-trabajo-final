package com.tecsup.pedidocomida.usuario.infrastructure.rest;

import com.tecsup.pedidocomida.usuario.application.CreateUserUseCase;
import com.tecsup.pedidocomida.usuario.domain.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final CreateUserUseCase useCase;

    public UserController(CreateUserUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@Valid @RequestBody CreateUserRequest request) {
        return useCase.create(request.name(), request.email());
    }

    @GetMapping
    public List<User> list() {
        return useCase.list();
    }
}

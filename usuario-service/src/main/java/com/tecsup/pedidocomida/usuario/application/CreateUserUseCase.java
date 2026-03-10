package com.tecsup.pedidocomida.usuario.application;

import com.tecsup.pedidocomida.usuario.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public CreateUserUseCase(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    public User create(String name, String email) {
        return userRepositoryPort.save(new User(null, name, email));
    }

    public List<User> list() {
        return userRepositoryPort.findAll();
    }
}

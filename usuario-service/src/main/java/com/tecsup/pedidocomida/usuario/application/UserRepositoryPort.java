package com.tecsup.pedidocomida.usuario.application;

import com.tecsup.pedidocomida.usuario.domain.User;

import java.util.List;

public interface UserRepositoryPort {
    User save(User user);

    List<User> findAll();
}

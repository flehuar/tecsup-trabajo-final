package com.tecsup.pedidocomida.usuario.infrastructure.persistence;

import com.tecsup.pedidocomida.usuario.application.UserRepositoryPort;
import com.tecsup.pedidocomida.usuario.domain.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserPersistenceAdapter implements UserRepositoryPort {

    private final SpringDataUserRepository repository;

    public UserPersistenceAdapter(SpringDataUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User save(User user) {
        UserJpaEntity entity = new UserJpaEntity();
        entity.setId(user.id());
        entity.setName(user.name());
        entity.setEmail(user.email());
        UserJpaEntity stored = repository.save(entity);
        return new User(stored.getId(), stored.getName(), stored.getEmail());
    }

    @Override
    public List<User> findAll() {
        return repository.findAll()
                .stream()
                .map(it -> new User(it.getId(), it.getName(), it.getEmail()))
                .toList();
    }
}

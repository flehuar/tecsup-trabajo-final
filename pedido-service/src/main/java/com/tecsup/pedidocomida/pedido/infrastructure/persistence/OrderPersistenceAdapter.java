package com.tecsup.pedidocomida.pedido.infrastructure.persistence;

import com.tecsup.pedidocomida.pedido.application.OrderRepositoryPort;
import com.tecsup.pedidocomida.pedido.domain.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OrderPersistenceAdapter implements OrderRepositoryPort {

    private final SpringDataOrderRepository repository;

    public OrderPersistenceAdapter(SpringDataOrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Order save(Order order) {
        OrderJpaEntity entity = new OrderJpaEntity();
        entity.setId(order.id());
        entity.setUserId(order.userId());
        entity.setTotalAmount(order.totalAmount());
        entity.setStatus(order.status());
        OrderJpaEntity stored = repository.save(entity);
        return new Order(stored.getId(), stored.getUserId(), stored.getTotalAmount(), stored.getStatus());
    }

    @Override
    public List<Order> findAll() {
        return repository.findAll()
                .stream()
                .map(it -> new Order(it.getId(), it.getUserId(), it.getTotalAmount(), it.getStatus()))
                .toList();
    }

    @Override
    public Optional<Order> findById(Long id) {
        return repository.findById(id)
                .map(it -> new Order(it.getId(), it.getUserId(), it.getTotalAmount(), it.getStatus()));
    }
}

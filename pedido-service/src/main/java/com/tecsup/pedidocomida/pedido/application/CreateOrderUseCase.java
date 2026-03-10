package com.tecsup.pedidocomida.pedido.application;

import com.tecsup.pedidocomida.pedido.domain.Order;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CreateOrderUseCase {

    private final OrderRepositoryPort repositoryPort;
    private final OrderEventPublisherPort eventPublisherPort;

    public CreateOrderUseCase(OrderRepositoryPort repositoryPort, OrderEventPublisherPort eventPublisherPort) {
        this.repositoryPort = repositoryPort;
        this.eventPublisherPort = eventPublisherPort;
    }

    public Order create(Long userId, BigDecimal totalAmount) {
        Order created = repositoryPort.save(new Order(null, userId, totalAmount, "CREATED"));
        eventPublisherPort.publishOrderCreated(created);
        return created;
    }

    public List<Order> list() {
        return repositoryPort.findAll();
    }
}

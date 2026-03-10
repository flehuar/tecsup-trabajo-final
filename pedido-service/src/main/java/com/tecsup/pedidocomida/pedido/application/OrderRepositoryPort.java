package com.tecsup.pedidocomida.pedido.application;

import com.tecsup.pedidocomida.pedido.domain.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepositoryPort {
    Order save(Order order);

    List<Order> findAll();

    Optional<Order> findById(Long id);
}

package com.tecsup.pedidocomida.entrega.application;

import com.tecsup.pedidocomida.entrega.domain.Delivery;

import java.util.List;
import java.util.Optional;

public interface DeliveryRepositoryPort {
    Delivery save(Delivery delivery);

    List<Delivery> findAll();

    Optional<Delivery> findByOrderId(Long orderId);
}

package com.tecsup.pedidocomida.pedido.application;

import com.tecsup.pedidocomida.pedido.domain.Order;

public interface OrderEventPublisherPort {
    void publishOrderCreated(Order order);

    void publishOrderStatusChanged(Order order);
}

package com.tecsup.pedidocomida.entrega.infrastructure.messaging;

import com.tecsup.pedidocomida.common.event.OrderCreatedEvent;
import com.tecsup.pedidocomida.entrega.application.RegisterDeliveryUseCase;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedDeliveryListener {

    private final RegisterDeliveryUseCase useCase;

    public OrderCreatedDeliveryListener(RegisterDeliveryUseCase useCase) {
        this.useCase = useCase;
    }

    @KafkaListener(topics = "order.created", groupId = "entrega-service-group")
    public void onOrderCreated(OrderCreatedEvent event) {
        useCase.registerPending(event.orderId());
    }
}

package com.tecsup.pedidocomida.pago.infrastructure.messaging;

import com.tecsup.pedidocomida.common.event.OrderCreatedEvent;
import com.tecsup.pedidocomida.pago.application.RegisterPaymentUseCase;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedPaymentListener {

    private final RegisterPaymentUseCase useCase;

    public OrderCreatedPaymentListener(RegisterPaymentUseCase useCase) {
        this.useCase = useCase;
    }

    @KafkaListener(topics = "order.created", groupId = "pago-service-group")
    public void onOrderCreated(OrderCreatedEvent event) {
        useCase.registerPending(event.orderId(), event.totalAmount());
    }
}

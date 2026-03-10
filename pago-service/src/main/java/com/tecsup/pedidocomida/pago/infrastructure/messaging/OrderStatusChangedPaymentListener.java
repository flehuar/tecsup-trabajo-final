package com.tecsup.pedidocomida.pago.infrastructure.messaging;

import com.tecsup.pedidocomida.common.event.OrderStatusChangedEvent;
import com.tecsup.pedidocomida.pago.application.RegisterPaymentUseCase;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderStatusChangedPaymentListener {

    private final RegisterPaymentUseCase useCase;

    public OrderStatusChangedPaymentListener(RegisterPaymentUseCase useCase) {
        this.useCase = useCase;
    }

    @KafkaListener(topics = "order.status.changed", groupId = "pago-service-group")
    public void onOrderStatusChanged(OrderStatusChangedEvent event) {
        useCase.updateStatusByOrderStatus(event.orderId(), event.status());
    }
}

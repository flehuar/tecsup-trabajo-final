package com.tecsup.pedidocomida.entrega.infrastructure.messaging;

import com.tecsup.pedidocomida.common.event.OrderStatusChangedEvent;
import com.tecsup.pedidocomida.entrega.application.RegisterDeliveryUseCase;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderStatusChangedDeliveryListener {

    private final RegisterDeliveryUseCase useCase;

    public OrderStatusChangedDeliveryListener(RegisterDeliveryUseCase useCase) {
        this.useCase = useCase;
    }

    @KafkaListener(topics = "order.status.changed", groupId = "entrega-service-group")
    public void onOrderStatusChanged(OrderStatusChangedEvent event) {
        useCase.updateStatusByOrderStatus(event.orderId(), event.status());
    }
}

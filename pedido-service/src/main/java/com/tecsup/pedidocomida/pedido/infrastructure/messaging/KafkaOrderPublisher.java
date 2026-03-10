package com.tecsup.pedidocomida.pedido.infrastructure.messaging;

import com.tecsup.pedidocomida.common.event.OrderCreatedEvent;
import com.tecsup.pedidocomida.common.event.OrderStatusChangedEvent;
import com.tecsup.pedidocomida.pedido.application.OrderEventPublisherPort;
import com.tecsup.pedidocomida.pedido.domain.Order;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaOrderPublisher implements OrderEventPublisherPort {

    private static final String ORDER_CREATED_TOPIC = "order.created";
    private static final String ORDER_STATUS_CHANGED_TOPIC = "order.status.changed";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaOrderPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publishOrderCreated(Order order) {
        OrderCreatedEvent event = new OrderCreatedEvent(order.id(), order.userId(), order.totalAmount(), order.status());
        kafkaTemplate.send(ORDER_CREATED_TOPIC, String.valueOf(order.id()), event);
    }

    @Override
    public void publishOrderStatusChanged(Order order) {
        OrderStatusChangedEvent event = new OrderStatusChangedEvent(order.id(), order.status());
        kafkaTemplate.send(ORDER_STATUS_CHANGED_TOPIC, String.valueOf(order.id()), event);
    }
}

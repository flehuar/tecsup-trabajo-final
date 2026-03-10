package com.tecsup.pedidocomida.common.event;

public record OrderStatusChangedEvent(Long orderId, String status) {
}

package com.tecsup.pedidocomida.common.event;

import java.math.BigDecimal;

public record OrderCreatedEvent(Long orderId, Long userId, BigDecimal totalAmount, String status) {
}

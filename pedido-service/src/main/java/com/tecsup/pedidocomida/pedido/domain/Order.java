package com.tecsup.pedidocomida.pedido.domain;

import java.math.BigDecimal;
import java.util.List;

public record Order(Long id, Long userId, BigDecimal totalAmount, String status, List<OrderItem> items) {
}

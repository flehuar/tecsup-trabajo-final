package com.tecsup.pedidocomida.pedido.domain;

import java.math.BigDecimal;

public record Order(Long id, Long userId, BigDecimal totalAmount, String status) {
}

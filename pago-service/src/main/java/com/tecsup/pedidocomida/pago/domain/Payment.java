package com.tecsup.pedidocomida.pago.domain;

import java.math.BigDecimal;

public record Payment(Long id, Long orderId, BigDecimal amount, String status) {
}

package com.tecsup.pedidocomida.pago.application;

import com.tecsup.pedidocomida.pago.domain.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentRepositoryPort {
    Payment save(Payment payment);

    List<Payment> findAll();

    Optional<Payment> findByOrderId(Long orderId);
}

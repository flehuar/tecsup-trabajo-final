package com.tecsup.pedidocomida.pago.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataPaymentRepository extends JpaRepository<PaymentJpaEntity, Long> {
    Optional<PaymentJpaEntity> findTopByOrderIdOrderByIdDesc(Long orderId);
}

package com.tecsup.pedidocomida.entrega.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataDeliveryRepository extends JpaRepository<DeliveryJpaEntity, Long> {
    Optional<DeliveryJpaEntity> findTopByOrderIdOrderByIdDesc(Long orderId);
}

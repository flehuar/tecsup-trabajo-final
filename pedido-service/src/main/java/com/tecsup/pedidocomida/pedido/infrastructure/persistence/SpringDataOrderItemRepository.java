package com.tecsup.pedidocomida.pedido.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataOrderItemRepository extends JpaRepository<OrderItemJpaEntity, Long> {
    List<OrderItemJpaEntity> findByOrderId(Long orderId);

    void deleteByOrderId(Long orderId);
}

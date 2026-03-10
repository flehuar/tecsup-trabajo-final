package com.tecsup.pedidocomida.pedido.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataOrderRepository extends JpaRepository<OrderJpaEntity, Long> {
}

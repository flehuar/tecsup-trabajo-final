package com.tecsup.pedidocomida.catalogo.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataProductRepository extends JpaRepository<ProductJpaEntity, Long> {
}

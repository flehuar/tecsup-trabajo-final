package com.tecsup.pedidocomida.catalogo.domain;

import java.math.BigDecimal;

public record Product(Long id, String name, String restaurant, BigDecimal price) {
}

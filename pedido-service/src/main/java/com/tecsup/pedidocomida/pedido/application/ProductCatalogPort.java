package com.tecsup.pedidocomida.pedido.application;

import java.util.Map;
import java.util.Set;

public interface ProductCatalogPort {
    Map<Long, java.math.BigDecimal> getProductPricesById(Set<Long> productIds, String authorizationHeader);
}

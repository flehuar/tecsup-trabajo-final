package com.tecsup.pedidocomida.pedido.application;

import com.tecsup.pedidocomida.pedido.domain.Order;
import com.tecsup.pedidocomida.pedido.domain.OrderItem;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class CreateOrderUseCase {

    private final OrderRepositoryPort repositoryPort;
    private final OrderEventPublisherPort eventPublisherPort;
    private final ProductCatalogPort productCatalogPort;

    public CreateOrderUseCase(OrderRepositoryPort repositoryPort,
                              OrderEventPublisherPort eventPublisherPort,
                              ProductCatalogPort productCatalogPort) {
        this.repositoryPort = repositoryPort;
        this.eventPublisherPort = eventPublisherPort;
        this.productCatalogPort = productCatalogPort;
    }

    public Order create(Long userId, List<OrderItem> items, String authorizationHeader) {
        List<OrderItem> normalizedItems = normalizeItems(items);
        Set<Long> productIds = normalizedItems.stream().map(OrderItem::productId).collect(java.util.stream.Collectors.toSet());

        Map<Long, BigDecimal> prices = productCatalogPort.getProductPricesById(productIds, authorizationHeader);
        if (prices.size() != productIds.size()) {
            throw new ResponseStatusException(BAD_REQUEST, "Uno o mas productos no existen en catalogo");
        }

        BigDecimal totalAmount = normalizedItems.stream()
                .map(it -> prices.get(it.productId()).multiply(BigDecimal.valueOf(it.quantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Order created = repositoryPort.save(new Order(null, userId, totalAmount, "CREATED", normalizedItems));
        eventPublisherPort.publishOrderCreated(created);
        return created;
    }

    private List<OrderItem> normalizeItems(List<OrderItem> items) {
        if (items == null || items.isEmpty()) {
            throw new ResponseStatusException(BAD_REQUEST, "Debe enviar al menos un item en el pedido");
        }

        Map<Long, Integer> qtyByProduct = new HashMap<>();
        for (OrderItem item : items) {
            if (item.productId() == null || item.quantity() == null || item.quantity() <= 0) {
                throw new ResponseStatusException(BAD_REQUEST, "Cada item debe tener productId y quantity > 0");
            }
            qtyByProduct.merge(item.productId(), item.quantity(), Integer::sum);
        }

        List<OrderItem> normalized = new ArrayList<>();
        for (Map.Entry<Long, Integer> entry : qtyByProduct.entrySet()) {
            normalized.add(new OrderItem(entry.getKey(), entry.getValue()));
        }
        return normalized;
    }

    public List<Order> list() {
        return repositoryPort.findAll();
    }
}

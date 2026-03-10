package com.tecsup.pedidocomida.pedido.application;

import com.tecsup.pedidocomida.pedido.domain.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.Set;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class UpdateOrderStatusUseCase {

    private static final Map<String, Set<String>> ALLOWED_TRANSITIONS = Map.of(
            "CREATED", Set.of("PAYMENT_CONFIRMED"),
            "PAYMENT_CONFIRMED", Set.of("PREPARING"),
            "PREPARING", Set.of("OUT_FOR_DELIVERY"),
            "OUT_FOR_DELIVERY", Set.of("DELIVERED"),
            "DELIVERED", Set.of("FINALIZED")
    );

    private final OrderRepositoryPort repositoryPort;
    private final OrderEventPublisherPort eventPublisherPort;

    public UpdateOrderStatusUseCase(OrderRepositoryPort repositoryPort, OrderEventPublisherPort eventPublisherPort) {
        this.repositoryPort = repositoryPort;
        this.eventPublisherPort = eventPublisherPort;
    }

    public Order updateStatus(Long orderId, String newStatus) {
        String normalizedStatus = newStatus == null ? "" : newStatus.trim().toUpperCase();
        Order existing = repositoryPort.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Order no encontrada"));

        if (existing.status().equalsIgnoreCase(normalizedStatus)) {
            return existing;
        }

        Set<String> allowed = ALLOWED_TRANSITIONS.getOrDefault(existing.status(), Set.of());
        if (!allowed.contains(normalizedStatus)) {
            throw new ResponseStatusException(BAD_REQUEST,
                    "Transicion invalida: " + existing.status() + " -> " + normalizedStatus);
        }

        Order updated = repositoryPort.save(new Order(existing.id(), existing.userId(), existing.totalAmount(), normalizedStatus, existing.items()));
        eventPublisherPort.publishOrderStatusChanged(updated);
        return updated;
    }
}

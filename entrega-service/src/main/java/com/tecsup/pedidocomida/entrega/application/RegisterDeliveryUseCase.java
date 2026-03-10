package com.tecsup.pedidocomida.entrega.application;

import com.tecsup.pedidocomida.entrega.domain.Delivery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RegisterDeliveryUseCase {

    private final DeliveryRepositoryPort repositoryPort;

    public RegisterDeliveryUseCase(DeliveryRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    public Delivery registerPending(Long orderId) {
        Delivery existing = repositoryPort.findByOrderId(orderId).orElse(null);
        if (existing != null) {
            return existing;
        }
        return repositoryPort.save(new Delivery(null, orderId, "PENDING_ASSIGNMENT"));
    }

    public List<Delivery> list() {
        return repositoryPort.findAll();
    }

    public Delivery updateStatusByOrderStatus(Long orderId, String orderStatus) {
        Delivery delivery = repositoryPort.findByOrderId(orderId).orElse(null);
        if (delivery == null) {
            return null;
        }

        String mappedStatus = mapDeliveryStatus(orderStatus);
        if (mappedStatus == null || mappedStatus.equals(delivery.status())) {
            return delivery;
        }

        return repositoryPort.save(new Delivery(delivery.id(), delivery.orderId(), mappedStatus));
    }

    private String mapDeliveryStatus(String orderStatus) {
        Map<String, String> mapping = Map.of(
                "PREPARING", "PREPARING",
                "OUT_FOR_DELIVERY", "EN_ROUTE",
                "DELIVERED", "DELIVERED",
                "FINALIZED", "FINALIZED"
        );
        return mapping.get(orderStatus);
    }
}

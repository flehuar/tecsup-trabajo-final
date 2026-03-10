package com.tecsup.pedidocomida.entrega.infrastructure.persistence;

import com.tecsup.pedidocomida.entrega.application.DeliveryRepositoryPort;
import com.tecsup.pedidocomida.entrega.domain.Delivery;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DeliveryPersistenceAdapter implements DeliveryRepositoryPort {

    private final SpringDataDeliveryRepository repository;

    public DeliveryPersistenceAdapter(SpringDataDeliveryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Delivery save(Delivery delivery) {
        DeliveryJpaEntity entity = new DeliveryJpaEntity();
        entity.setId(delivery.id());
        entity.setOrderId(delivery.orderId());
        entity.setStatus(delivery.status());
        DeliveryJpaEntity stored = repository.save(entity);
        return new Delivery(stored.getId(), stored.getOrderId(), stored.getStatus());
    }

    @Override
    public List<Delivery> findAll() {
        return repository.findAll()
                .stream()
                .map(it -> new Delivery(it.getId(), it.getOrderId(), it.getStatus()))
                .toList();
    }

    @Override
    public Optional<Delivery> findByOrderId(Long orderId) {
        return repository.findTopByOrderIdOrderByIdDesc(orderId)
                .map(it -> new Delivery(it.getId(), it.getOrderId(), it.getStatus()));
    }
}

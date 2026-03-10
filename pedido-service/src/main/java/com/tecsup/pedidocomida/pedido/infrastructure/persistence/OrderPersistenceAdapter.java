package com.tecsup.pedidocomida.pedido.infrastructure.persistence;

import com.tecsup.pedidocomida.pedido.application.OrderRepositoryPort;
import com.tecsup.pedidocomida.pedido.domain.Order;
import com.tecsup.pedidocomida.pedido.domain.OrderItem;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class OrderPersistenceAdapter implements OrderRepositoryPort {

    private final SpringDataOrderRepository repository;
    private final SpringDataOrderItemRepository itemRepository;

    public OrderPersistenceAdapter(SpringDataOrderRepository repository,
                                   SpringDataOrderItemRepository itemRepository) {
        this.repository = repository;
        this.itemRepository = itemRepository;
    }

    @Override
    @Transactional
    public Order save(Order order) {
        OrderJpaEntity entity = new OrderJpaEntity();
        entity.setId(order.id());
        entity.setUserId(order.userId());
        entity.setTotalAmount(order.totalAmount());
        entity.setStatus(order.status());
        OrderJpaEntity stored = repository.save(entity);

        itemRepository.deleteByOrderId(stored.getId());
        if (order.items() != null) {
            for (OrderItem item : order.items()) {
                OrderItemJpaEntity itemEntity = new OrderItemJpaEntity();
                itemEntity.setOrderId(stored.getId());
                itemEntity.setProductId(item.productId());
                itemEntity.setQuantity(item.quantity());
                itemRepository.save(itemEntity);
            }
        }

        return mapToDomain(stored);
    }

    @Override
    public List<Order> findAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToDomain)
                .toList();
    }

    @Override
    public Optional<Order> findById(Long id) {
        return repository.findById(id)
                .map(this::mapToDomain);
    }

    private Order mapToDomain(OrderJpaEntity entity) {
        List<OrderItem> items = itemRepository.findByOrderId(entity.getId()).stream()
                .map(it -> new OrderItem(it.getProductId(), it.getQuantity()))
                .toList();
        return new Order(entity.getId(), entity.getUserId(), entity.getTotalAmount(), entity.getStatus(), items);
    }
}

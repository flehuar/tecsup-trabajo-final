package com.tecsup.pedidocomida.pago.infrastructure.persistence;

import com.tecsup.pedidocomida.pago.application.PaymentRepositoryPort;
import com.tecsup.pedidocomida.pago.domain.Payment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PaymentPersistenceAdapter implements PaymentRepositoryPort {

    private final SpringDataPaymentRepository repository;

    public PaymentPersistenceAdapter(SpringDataPaymentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Payment save(Payment payment) {
        PaymentJpaEntity entity = new PaymentJpaEntity();
        entity.setId(payment.id());
        entity.setOrderId(payment.orderId());
        entity.setAmount(payment.amount());
        entity.setStatus(payment.status());
        PaymentJpaEntity stored = repository.save(entity);
        return new Payment(stored.getId(), stored.getOrderId(), stored.getAmount(), stored.getStatus());
    }

    @Override
    public List<Payment> findAll() {
        return repository.findAll()
                .stream()
                .map(it -> new Payment(it.getId(), it.getOrderId(), it.getAmount(), it.getStatus()))
                .toList();
    }

    @Override
    public Optional<Payment> findByOrderId(Long orderId) {
        return repository.findTopByOrderIdOrderByIdDesc(orderId)
                .map(it -> new Payment(it.getId(), it.getOrderId(), it.getAmount(), it.getStatus()));
    }
}

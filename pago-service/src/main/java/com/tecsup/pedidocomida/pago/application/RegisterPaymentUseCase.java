package com.tecsup.pedidocomida.pago.application;

import com.tecsup.pedidocomida.pago.domain.Payment;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Service
public class RegisterPaymentUseCase {

    private final PaymentRepositoryPort repositoryPort;

    public RegisterPaymentUseCase(PaymentRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    public Payment registerPending(Long orderId, BigDecimal amount) {
        Payment existing = repositoryPort.findByOrderId(orderId).orElse(null);
        if (existing != null) {
            return existing;
        }
        return repositoryPort.save(new Payment(null, orderId, amount, "PENDING"));
    }

    public List<Payment> list() {
        return repositoryPort.findAll();
    }

    public Payment updateStatusByOrderStatus(Long orderId, String orderStatus) {
        Payment payment = repositoryPort.findByOrderId(orderId).orElse(null);
        if (payment == null) {
            return null;
        }

        String newPaymentStatus = mapPaymentStatus(orderStatus);
        if (newPaymentStatus == null || newPaymentStatus.equals(payment.status())) {
            return payment;
        }

        return repositoryPort.save(new Payment(payment.id(), payment.orderId(), payment.amount(), newPaymentStatus));
    }

    private String mapPaymentStatus(String orderStatus) {
        if (Set.of("PAYMENT_CONFIRMED", "PREPARING", "OUT_FOR_DELIVERY", "DELIVERED", "FINALIZED").contains(orderStatus)) {
            return "PAID";
        }
        return null;
    }
}

package com.tecsup.pedidocomida.pago.infrastructure.rest;

import com.tecsup.pedidocomida.pago.application.RegisterPaymentUseCase;
import com.tecsup.pedidocomida.pago.domain.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final RegisterPaymentUseCase useCase;

    public PaymentController(RegisterPaymentUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    public List<Payment> list() {
        return useCase.list();
    }
}

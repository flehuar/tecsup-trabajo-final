package com.tecsup.pedidocomida.entrega.infrastructure.rest;

import com.tecsup.pedidocomida.entrega.application.RegisterDeliveryUseCase;
import com.tecsup.pedidocomida.entrega.domain.Delivery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {

    private final RegisterDeliveryUseCase useCase;

    public DeliveryController(RegisterDeliveryUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    public List<Delivery> list() {
        return useCase.list();
    }
}

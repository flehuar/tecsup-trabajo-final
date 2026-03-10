package com.tecsup.pedidocomida.pedido.infrastructure.rest;

import com.tecsup.pedidocomida.pedido.application.CreateOrderUseCase;
import com.tecsup.pedidocomida.pedido.application.UpdateOrderStatusUseCase;
import com.tecsup.pedidocomida.pedido.domain.Order;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;
    private final UpdateOrderStatusUseCase updateOrderStatusUseCase;

    public OrderController(CreateOrderUseCase createOrderUseCase, UpdateOrderStatusUseCase updateOrderStatusUseCase) {
        this.createOrderUseCase = createOrderUseCase;
        this.updateOrderStatusUseCase = updateOrderStatusUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order create(@Valid @RequestBody CreateOrderRequest request) {
        return createOrderUseCase.create(request.userId(), request.totalAmount());
    }

    @PatchMapping("/{orderId}/status")
    public Order updateStatus(@PathVariable("orderId") Long orderId, @Valid @RequestBody UpdateOrderStatusRequest request) {
        return updateOrderStatusUseCase.updateStatus(orderId, request.status());
    }

    @GetMapping
    public List<Order> list() {
        return createOrderUseCase.list();
    }
}

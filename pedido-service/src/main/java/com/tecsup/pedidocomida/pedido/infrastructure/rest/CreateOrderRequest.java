package com.tecsup.pedidocomida.pedido.infrastructure.rest;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.Valid;

import java.util.List;

public record CreateOrderRequest(@NotNull Long userId,
                                 @NotEmpty List<@Valid CreateOrderItemRequest> items) {

    public record CreateOrderItemRequest(@NotNull Long productId,
                                         @NotNull @Positive Integer quantity) {
    }
}

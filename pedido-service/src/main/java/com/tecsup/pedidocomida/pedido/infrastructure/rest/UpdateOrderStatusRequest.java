package com.tecsup.pedidocomida.pedido.infrastructure.rest;

import jakarta.validation.constraints.NotBlank;

public record UpdateOrderStatusRequest(@NotBlank String status) {
}

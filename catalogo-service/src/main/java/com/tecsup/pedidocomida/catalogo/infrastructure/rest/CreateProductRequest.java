package com.tecsup.pedidocomida.catalogo.infrastructure.rest;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateProductRequest(@NotBlank String name,
                                   @NotBlank String restaurant,
                                   @NotNull @DecimalMin("0.01") BigDecimal price) {
}

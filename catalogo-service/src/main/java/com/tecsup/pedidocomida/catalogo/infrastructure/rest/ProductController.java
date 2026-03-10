package com.tecsup.pedidocomida.catalogo.infrastructure.rest;

import com.tecsup.pedidocomida.catalogo.application.CreateProductUseCase;
import com.tecsup.pedidocomida.catalogo.domain.Product;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final CreateProductUseCase useCase;

    public ProductController(CreateProductUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@Valid @RequestBody CreateProductRequest request) {
        return useCase.create(request.name(), request.restaurant(), request.price());
    }

    @GetMapping
    public List<Product> list() {
        return useCase.list();
    }
}

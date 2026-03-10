package com.tecsup.pedidocomida.catalogo.application;

import com.tecsup.pedidocomida.catalogo.domain.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CreateProductUseCase {

    private final ProductRepositoryPort repositoryPort;

    public CreateProductUseCase(ProductRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    public Product create(String name, String restaurant, BigDecimal price) {
        return repositoryPort.save(new Product(null, name, restaurant, price));
    }

    public List<Product> list() {
        return repositoryPort.findAll();
    }
}

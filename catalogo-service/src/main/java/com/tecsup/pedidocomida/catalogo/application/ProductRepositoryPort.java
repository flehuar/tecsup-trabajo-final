package com.tecsup.pedidocomida.catalogo.application;

import com.tecsup.pedidocomida.catalogo.domain.Product;

import java.util.List;

public interface ProductRepositoryPort {
    Product save(Product product);

    List<Product> findAll();
}

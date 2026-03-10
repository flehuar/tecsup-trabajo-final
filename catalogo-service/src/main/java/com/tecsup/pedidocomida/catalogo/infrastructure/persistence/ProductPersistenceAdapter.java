package com.tecsup.pedidocomida.catalogo.infrastructure.persistence;

import com.tecsup.pedidocomida.catalogo.application.ProductRepositoryPort;
import com.tecsup.pedidocomida.catalogo.domain.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductPersistenceAdapter implements ProductRepositoryPort {

    private final SpringDataProductRepository repository;

    public ProductPersistenceAdapter(SpringDataProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product save(Product product) {
        ProductJpaEntity entity = new ProductJpaEntity();
        entity.setId(product.id());
        entity.setName(product.name());
        entity.setRestaurant(product.restaurant());
        entity.setPrice(product.price());
        ProductJpaEntity stored = repository.save(entity);
        return new Product(stored.getId(), stored.getName(), stored.getRestaurant(), stored.getPrice());
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll()
                .stream()
                .map(it -> new Product(it.getId(), it.getName(), it.getRestaurant(), it.getPrice()))
                .toList();
    }
}

package com.zeroim.admin.adapters.product;

import com.zeroim.admin.domain.product.Product;
import com.zeroim.admin.ports.primary.product.ProductService;
import com.zeroim.admin.ports.secondary.product.ProductRepo;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Component
public class DefaultProductService implements ProductService {
    private final ProductRepo repo;

    public DefaultProductService(ProductRepo repo) {
        this.repo = repo;
    }

    @Override
    public Product create(Product product) {
        return repo.save(product);
    }

    @Override
    public Product getById(UUID id) {
        return repo.findById(id).get();
    }

    @Override
    public List<Product> getAll() {
        Iterable<Product> products = repo.findAll();
        return StreamSupport.stream(products.spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public List<Product> getByCategory(UUID categoryId) {
        Iterable<Product> products = repo.findAllByCategory(categoryId);
        return StreamSupport.stream(products.spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Product update(Product product) {
        Optional<Product> productDB = repo.findById(product.getId());
        if (productDB.isPresent()) {
            repo.delete(productDB.get());
            return repo.save(product);
        }
        return null;
    }
}

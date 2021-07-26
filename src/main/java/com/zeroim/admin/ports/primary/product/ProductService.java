package com.zeroim.admin.ports.primary.product;

import com.zeroim.admin.domain.product.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    Product create(Product product);

    Product getById(UUID id);

    List<Product> getAll();

    List<Product> getByCategory(UUID categoryId);

    Product update(Product product);
}

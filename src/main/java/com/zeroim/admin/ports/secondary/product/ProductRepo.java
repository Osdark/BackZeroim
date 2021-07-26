package com.zeroim.admin.ports.secondary.product;

import com.zeroim.admin.domain.product.Product;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepo extends CrudRepository<Product, UUID> {
    @Query("{'category': ?0}")
    Iterable<Product> findAllByCategory(UUID categoryId);
}

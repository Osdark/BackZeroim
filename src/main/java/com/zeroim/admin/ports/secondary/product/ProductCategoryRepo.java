package com.zeroim.admin.ports.secondary.product;

import com.zeroim.admin.domain.product.ProductCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductCategoryRepo extends CrudRepository<ProductCategory, UUID> {
}

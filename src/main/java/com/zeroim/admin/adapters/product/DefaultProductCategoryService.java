package com.zeroim.admin.adapters.product;

import com.zeroim.admin.domain.product.ProductCategory;
import com.zeroim.admin.ports.primary.product.ProductCategoryService;
import com.zeroim.admin.ports.secondary.product.ProductCategoryRepo;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class DefaultProductCategoryService implements ProductCategoryService {
    private final ProductCategoryRepo repo;

    public DefaultProductCategoryService(ProductCategoryRepo repo) {
        this.repo = repo;
    }

    @Override
    public ProductCategory create(String category) {
        ProductCategory productCategory = new ProductCategory(UUID.randomUUID(), category);
        return repo.save(productCategory);
    }

    @Override
    public List<ProductCategory> getAll() {
        Iterable<ProductCategory> productCategories = repo.findAll();
        return StreamSupport.stream(productCategories.spliterator(), false).collect(Collectors.toList());
    }
}

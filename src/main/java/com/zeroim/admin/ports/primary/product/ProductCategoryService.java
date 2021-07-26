package com.zeroim.admin.ports.primary.product;

import com.zeroim.admin.domain.product.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    ProductCategory create(String category);

    List<ProductCategory> getAll();
}

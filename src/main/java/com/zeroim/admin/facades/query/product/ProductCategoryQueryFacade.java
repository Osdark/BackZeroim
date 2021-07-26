package com.zeroim.admin.facades.query.product;

import com.zeroim.admin.requests.product.ProductCategoryDTO;

import java.util.List;

public interface ProductCategoryQueryFacade {
    List<ProductCategoryDTO> getAll();
}

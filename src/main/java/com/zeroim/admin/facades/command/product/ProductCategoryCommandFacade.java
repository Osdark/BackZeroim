package com.zeroim.admin.facades.command.product;

import com.zeroim.admin.requests.product.ProductCategoryDTO;

public interface ProductCategoryCommandFacade {
    ProductCategoryDTO create(String category);
}

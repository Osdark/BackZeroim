package com.zeroim.admin.populators.product;

import com.zeroim.admin.domain.product.ProductCategory;
import com.zeroim.admin.populators.Populator;
import com.zeroim.admin.requests.product.ProductCategoryDTO;

public class ProductCategoryResponsePopulator implements Populator<ProductCategory, ProductCategoryDTO> {
    @Override
    public void populate(ProductCategory productCategory, ProductCategoryDTO productCategoryDTO) {
        productCategoryDTO.setId(productCategory.getId());
        productCategoryDTO.setName(productCategory.getName());
    }
}

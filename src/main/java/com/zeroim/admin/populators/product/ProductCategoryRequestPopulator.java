package com.zeroim.admin.populators.product;

import com.zeroim.admin.domain.product.ProductCategory;
import com.zeroim.admin.populators.Populator;
import com.zeroim.admin.requests.product.ProductCategoryDTO;
import org.springframework.security.core.parameters.P;

public class ProductCategoryRequestPopulator implements Populator<ProductCategoryDTO, ProductCategory> {
    @Override
    public void populate(ProductCategoryDTO productCategoryDTO, ProductCategory productCategory) {
        productCategory.setId(productCategoryDTO.getId());
        productCategory.setName(productCategoryDTO.getName());
    }
}

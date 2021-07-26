package com.zeroim.admin.populators.product;

import com.zeroim.admin.domain.product.Product;
import com.zeroim.admin.populators.Populator;
import com.zeroim.admin.requests.product.ProductDTO;

import java.util.UUID;

public class ProductRequestPopulator implements Populator<ProductDTO, Product> {
    @Override
    public void populate(ProductDTO productDTO, Product product) {
        product.setId(UUID.randomUUID());
        product.setCategory(productDTO.getCategory());
        product.setCode(productDTO.getCode());
        product.setPrice(productDTO.getPrice());
        product.setName(productDTO.getName());
        product.setUnits(productDTO.getUnits());
        product.setWeight(productDTO.getWeight());
    }
}

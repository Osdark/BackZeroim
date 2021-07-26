package com.zeroim.admin.populators.product;

import com.zeroim.admin.domain.product.Product;
import com.zeroim.admin.populators.Populator;
import com.zeroim.admin.requests.product.ProductDTO;

public class ProductResponsePopulator implements Populator<Product, ProductDTO> {
    @Override
    public void populate(Product product, ProductDTO productDTO) {
        productDTO.setCategory(product.getCategory());
        productDTO.setCode(product.getCode());
        productDTO.setPrice(product.getPrice());
        productDTO.setName(product.getName());
        productDTO.setUnits(product.getUnits());
        productDTO.setWeight(product.getWeight());
    }
}

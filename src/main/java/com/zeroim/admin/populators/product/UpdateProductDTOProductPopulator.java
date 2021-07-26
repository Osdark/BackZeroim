package com.zeroim.admin.populators.product;

import com.zeroim.admin.domain.product.Product;
import com.zeroim.admin.populators.Populator;
import com.zeroim.admin.requests.product.RequestUpdateProductDTO;

public class UpdateProductDTOProductPopulator implements Populator<RequestUpdateProductDTO, Product> {
    @Override
    public void populate(RequestUpdateProductDTO requestUpdateProductDTO, Product product) {
        product.setId(requestUpdateProductDTO.getId());
        product.setCategory(requestUpdateProductDTO.getCategory());
        product.setCode(requestUpdateProductDTO.getCode());
        product.setPrice(requestUpdateProductDTO.getPrice());
        product.setName(requestUpdateProductDTO.getName());
        product.setWeight(requestUpdateProductDTO.getWeight());
        product.setUnits(requestUpdateProductDTO.getUnits());
    }
}

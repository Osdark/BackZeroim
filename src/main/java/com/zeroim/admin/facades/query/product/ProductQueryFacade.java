package com.zeroim.admin.facades.query.product;

import com.zeroim.admin.requests.product.ProductDTO;

import java.util.List;
import java.util.UUID;

public interface ProductQueryFacade {
    ProductDTO getById(UUID id);

    List<ProductDTO> getByCategory(UUID id);

    List<ProductDTO> getAll();
}

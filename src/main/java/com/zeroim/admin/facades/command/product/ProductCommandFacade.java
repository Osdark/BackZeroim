package com.zeroim.admin.facades.command.product;

import com.zeroim.admin.requests.product.ProductDTO;
import com.zeroim.admin.requests.product.RequestUpdateProductDTO;

public interface ProductCommandFacade {
    ProductDTO create(ProductDTO productDTO);

    ProductDTO update(RequestUpdateProductDTO productDTO);
}

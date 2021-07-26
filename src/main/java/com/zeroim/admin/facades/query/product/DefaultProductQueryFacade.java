package com.zeroim.admin.facades.query.product;

import com.zeroim.admin.converter.Converter;
import com.zeroim.admin.domain.product.Product;
import com.zeroim.admin.populators.Populator;
import com.zeroim.admin.populators.product.ProductResponsePopulator;
import com.zeroim.admin.ports.primary.product.ProductService;
import com.zeroim.admin.requests.product.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class DefaultProductQueryFacade implements ProductQueryFacade {
    @Autowired
    private final ProductService service;

    public DefaultProductQueryFacade(ProductService service) {
        this.service = service;
    }

    @Override
    public ProductDTO getById(UUID id) {
        return convertProduct(service.getById(id));
    }

    @Override
    public List<ProductDTO> getByCategory(UUID id) {
        return convertProductList(service.getByCategory(id));
    }

    @Override
    public List<ProductDTO> getAll() {
        return convertProductList(service.getAll());
    }

    private List<ProductDTO> convertProductList(List<Product> products) {
        Converter<Product, ProductDTO> responseConverter = buildResponseConverter();
        return responseConverter.convertAll(products);
    }

    private ProductDTO convertProduct(Product product) {
        Converter<Product, ProductDTO> responseConverter = buildResponseConverter();
        return responseConverter.convert(product);
    }

    private Converter<Product, ProductDTO> buildResponseConverter() {
        Populator<Product, ProductDTO> responsePopulator = new ProductResponsePopulator();
        return Converter.of(ProductDTO.class).withPopulator(responsePopulator);
    }
}

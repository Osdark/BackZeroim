package com.zeroim.admin.facades.command.product;

import com.zeroim.admin.converter.Converter;
import com.zeroim.admin.domain.product.ProductCategory;
import com.zeroim.admin.populators.Populator;
import com.zeroim.admin.populators.product.ProductCategoryRequestPopulator;
import com.zeroim.admin.populators.product.ProductCategoryResponsePopulator;
import com.zeroim.admin.ports.primary.product.ProductCategoryService;
import com.zeroim.admin.requests.product.ProductCategoryDTO;
import com.zeroim.admin.requests.product.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultProductCategoryCommandFacade implements ProductCategoryCommandFacade {
    @Autowired
    private final ProductCategoryService service;

    public DefaultProductCategoryCommandFacade(ProductCategoryService service) {
        this.service = service;
    }

    @Override
    public ProductCategoryDTO create(String category) {
        return convertProductCategory(service.create(category));
    }

    private ProductCategoryDTO convertProductCategory(ProductCategory productCategory) {
        Converter<ProductCategory, ProductCategoryDTO> responseConverter = buildResponseConverter();
        return responseConverter.convert(productCategory);
    }

    private Converter<ProductCategory, ProductCategoryDTO> buildResponseConverter() {
        Populator<ProductCategory, ProductCategoryDTO> responsePopulator = new ProductCategoryResponsePopulator();
        return Converter.of(ProductCategoryDTO.class).withPopulator(responsePopulator);
    }
}

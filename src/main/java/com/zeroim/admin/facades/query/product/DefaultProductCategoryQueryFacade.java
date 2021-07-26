package com.zeroim.admin.facades.query.product;

import com.zeroim.admin.converter.Converter;
import com.zeroim.admin.domain.product.ProductCategory;
import com.zeroim.admin.populators.Populator;
import com.zeroim.admin.populators.product.ProductCategoryResponsePopulator;
import com.zeroim.admin.ports.primary.product.ProductCategoryService;
import com.zeroim.admin.requests.product.ProductCategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultProductCategoryQueryFacade implements ProductCategoryQueryFacade {
    @Autowired
    private final ProductCategoryService service;

    public DefaultProductCategoryQueryFacade(ProductCategoryService service) {
        this.service = service;
    }

    @Override
    public List<ProductCategoryDTO> getAll() {
        return convertProuctCategoryList(service.getAll());
    }

    private List<ProductCategoryDTO> convertProuctCategoryList(List<ProductCategory> productCategories) {
        Converter<ProductCategory, ProductCategoryDTO> responseConverter = buildResponseConverter();
        return responseConverter.convertAll(productCategories);
    }

    private Converter<ProductCategory, ProductCategoryDTO> buildResponseConverter() {
        Populator<ProductCategory, ProductCategoryDTO> responsePopulator = new ProductCategoryResponsePopulator();
        return Converter.of(ProductCategoryDTO.class).withPopulator(responsePopulator);
    }
}

package com.zeroim.admin.facades.command.product;

import com.zeroim.admin.converter.Converter;
import com.zeroim.admin.domain.product.Product;
import com.zeroim.admin.populators.Populator;
import com.zeroim.admin.populators.product.ProductRequestPopulator;
import com.zeroim.admin.populators.product.ProductResponsePopulator;
import com.zeroim.admin.populators.product.UpdateProductDTOProductPopulator;
import com.zeroim.admin.ports.primary.product.ProductService;
import com.zeroim.admin.requests.product.ProductDTO;
import com.zeroim.admin.requests.product.RequestUpdateProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultProductCommandFacade implements ProductCommandFacade {
    @Autowired
    private final ProductService service;

    public DefaultProductCommandFacade(ProductService service) {
        this.service = service;
    }

    @Override
    public ProductDTO create(ProductDTO productDTO) {
        return convertProdcut(service.create(convertProductDTO(productDTO)));
    }

    @Override
    public ProductDTO update(RequestUpdateProductDTO productDTO) {
        return convertProdcut(service.create(convertUpdateProductDTO(productDTO)));
    }

    private Product convertUpdateProductDTO(RequestUpdateProductDTO productDTO) {
        Converter<RequestUpdateProductDTO, Product> updateProductDTOProductConverter = buildUpdateProductDTOConverter();
        return updateProductDTOProductConverter.convert(productDTO);
    }

    private Converter<RequestUpdateProductDTO, Product> buildUpdateProductDTOConverter() {
        Populator<RequestUpdateProductDTO, Product> updateProductDTOProductPopulator = new UpdateProductDTOProductPopulator();
        return Converter.of(Product.class).withPopulator(updateProductDTOProductPopulator);
    }

    private ProductDTO convertProdcut(Product product) {
        Converter<Product, ProductDTO> responseConverter = buildResponseConverter();
        return responseConverter.convert(product);
    }

    private Converter<Product, ProductDTO> buildResponseConverter() {
        Populator<Product, ProductDTO> responsePopulator = new ProductResponsePopulator();
        return Converter.of(ProductDTO.class).withPopulator(responsePopulator);
    }

    private Product convertProductDTO(ProductDTO productDTO) {
        Converter<ProductDTO, Product> requestConverter = buildRequestConverter();
        return requestConverter.convert(productDTO);
    }

    private Converter<ProductDTO, Product> buildRequestConverter() {
        Populator<ProductDTO, Product> requestPopulator = new ProductRequestPopulator();
        return Converter.of(Product.class).withPopulator(requestPopulator);
    }
}

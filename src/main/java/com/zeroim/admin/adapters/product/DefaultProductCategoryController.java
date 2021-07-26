package com.zeroim.admin.adapters.product;

import com.zeroim.admin.adapters.util.ControllerUtils;
import com.zeroim.admin.facades.command.product.ProductCategoryCommandFacade;
import com.zeroim.admin.facades.query.product.ProductCategoryQueryFacade;
import com.zeroim.admin.ports.primary.product.ProductCategoryController;
import com.zeroim.admin.requests.product.ProductCategoryDTO;
import com.zeroim.admin.requests.util.ResError;
import com.zeroim.admin.requests.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
public class DefaultProductCategoryController implements ProductCategoryController {
    @Autowired
    private final ProductCategoryCommandFacade commandFacade;
    @Autowired
    private final ProductCategoryQueryFacade queryFacade;
    @Autowired
    private final ControllerUtils utils;

    public DefaultProductCategoryController(ProductCategoryCommandFacade commandFacade, ProductCategoryQueryFacade queryFacade, ControllerUtils utils) {
        this.commandFacade = commandFacade;
        this.queryFacade = queryFacade;
        this.utils = utils;
    }

    @Override
    public ResponseEntity<Response<ProductCategoryDTO>> create(String category) {
        Response<ProductCategoryDTO> response = new Response<>();
        ResError error = new ResError();
        ProductCategoryDTO productCategoryCreated = commandFacade.create(category);
        response.setData(productCategoryCreated);

        if (Objects.isNull(productCategoryCreated)) {
            return utils.getBadResponseEntity(response, error, HttpStatus.NOT_FOUND, "Category not created");
        } else {
            return utils.getResponseEntityOk(response, error);
        }
    }

    @Override
    public ResponseEntity<Response<List<ProductCategoryDTO>>> getAll() {
        Response<List<ProductCategoryDTO>> response = new Response<>();
        ResError error = new ResError();
        List<ProductCategoryDTO> productCategoryDTOS = queryFacade.getAll();
        response.setData(productCategoryDTOS);

        if (Objects.isNull(productCategoryDTOS)) {
            return utils.getBadResponseEntity(response, error, HttpStatus.NOT_FOUND, "Category list not found");
        } else {
            return utils.getResponseEntityOk(response, error);
        }
    }
}

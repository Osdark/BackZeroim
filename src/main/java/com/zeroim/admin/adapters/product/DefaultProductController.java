package com.zeroim.admin.adapters.product;

import com.zeroim.admin.adapters.util.ControllerUtils;
import com.zeroim.admin.facades.command.product.ProductCommandFacade;
import com.zeroim.admin.facades.query.product.ProductQueryFacade;
import com.zeroim.admin.ports.primary.product.ProductController;
import com.zeroim.admin.requests.product.ProductDTO;
import com.zeroim.admin.requests.product.RequestUpdateProductDTO;
import com.zeroim.admin.requests.util.ResError;
import com.zeroim.admin.requests.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
public class DefaultProductController implements ProductController {
    @Autowired
    private final ProductCommandFacade commandFacade;
    @Autowired
    private final ProductQueryFacade queryFacade;
    @Autowired
    private final ControllerUtils utils;

    public DefaultProductController(ProductCommandFacade commandFacade, ProductQueryFacade queryFacade, ControllerUtils utils) {
        this.commandFacade = commandFacade;
        this.queryFacade = queryFacade;
        this.utils = utils;
    }

    @Override
    public ResponseEntity<Response<ProductDTO>> create(ProductDTO productDTO) {
        Response<ProductDTO> response = new Response<>();
        ResError error = new ResError();
        ProductDTO productCreated = commandFacade.create(productDTO);
        response.setData(productCreated);

        if (Objects.isNull(productCreated)) {
            return utils.getBadResponseEntity(response, error, HttpStatus.NOT_FOUND, "Product not created");
        } else {
            return utils.getResponseEntityOk(response, error);
        }
    }

    @Override
    public ResponseEntity<Response<ProductDTO>> getById(UUID id) {
        Response<ProductDTO> response = new Response<>();
        ResError error = new ResError();
        ProductDTO productDTO = queryFacade.getById(id);
        response.setData(productDTO);

        if (Objects.isNull(productDTO)) {
            return utils.getBadResponseEntity(response, error, HttpStatus.NOT_FOUND, "Product not found");
        } else {
            return utils.getResponseEntityOk(response, error);
        }
    }

    @Override
    public ResponseEntity<Response<List<ProductDTO>>> getAll() {
        Response<List<ProductDTO>> response = new Response<>();
        ResError error = new ResError();
        List<ProductDTO> productDTOList = queryFacade.getAll();
        response.setData(productDTOList);

        if (Objects.isNull(productDTOList)) {
            return utils.getBadResponseEntity(response, error, HttpStatus.NOT_FOUND, "Product list not found");
        } else {
            return utils.getResponseEntityOk(response, error);
        }
    }

    @Override
    public ResponseEntity<Response<List<ProductDTO>>> getByCategory(UUID categoryId) {
        Response<List<ProductDTO>> response = new Response<>();
        ResError error = new ResError();
        List<ProductDTO> productDTOList = queryFacade.getByCategory(categoryId);
        response.setData(productDTOList);

        if (Objects.isNull(productDTOList)) {
            return utils.getBadResponseEntity(response, error, HttpStatus.NOT_FOUND, "Product list not found");
        } else {
            return utils.getResponseEntityOk(response, error);
        }
    }

    @Override
    public ResponseEntity<Response<ProductDTO>> update(RequestUpdateProductDTO productDTO) {
        Response<ProductDTO> response = new Response<>();
        ResError error = new ResError();
        ProductDTO productUpdated = commandFacade.update(productDTO);
        response.setData(productUpdated);

        if (Objects.isNull(productUpdated)) {
            return utils.getBadResponseEntity(response, error, HttpStatus.NOT_FOUND, "Product not updated");
        } else {
            return utils.getResponseEntityOk(response, error);
        }
    }
}

package com.zeroim.admin.adapters.buyer;

import com.zeroim.admin.adapters.util.ControllerUtils;
import com.zeroim.admin.facades.command.buyer.BuyerCommandFacade;
import com.zeroim.admin.facades.query.buyer.BuyerQueryFacade;
import com.zeroim.admin.ports.primary.buyer.BuyerController;
import com.zeroim.admin.requests.buyer.BuyerDTO;
import com.zeroim.admin.requests.buyer.RequestCreateBuyerDTO;
import com.zeroim.admin.requests.util.ResError;
import com.zeroim.admin.requests.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
public class DefaultBuyerController implements BuyerController {
    @Autowired
    private final BuyerCommandFacade commandFacade;
    @Autowired
    private final BuyerQueryFacade queryFacade;
    @Autowired
    private final ControllerUtils utils;

    public DefaultBuyerController(BuyerCommandFacade commandFacade, BuyerQueryFacade queryFacade, ControllerUtils utils) {
        this.commandFacade = commandFacade;
        this.queryFacade = queryFacade;
        this.utils = utils;
    }

    @Override
    public ResponseEntity<Response<BuyerDTO>> create(RequestCreateBuyerDTO buyerDTO) {
        Response<BuyerDTO> response = new Response<>();
        ResError error = new ResError();
        BuyerDTO buyerResponse = commandFacade.create(buyerDTO);
        response.setData(buyerResponse);

        if (Objects.isNull(buyerResponse)) {
            return utils.getBadResponseEntity(response, error, HttpStatus.NOT_FOUND, "Buyer not created");
        } else {
            return utils.getResponseEntityOk(response, error);
        }
    }

    @Override
    public ResponseEntity<Response<BuyerDTO>> getById(UUID id) {
        Response<BuyerDTO> response = new Response<>();
        ResError error = new ResError();
        BuyerDTO buyerDTO = queryFacade.getById(id);
        response.setData(buyerDTO);

        if (Objects.isNull(buyerDTO)) {
            return utils.getBadResponseEntity(response, error, HttpStatus.NOT_FOUND, "Buyer not found");
        } else {
            return utils.getResponseEntityOk(response, error);
        }
    }

    @Override
    public ResponseEntity<Response<List<BuyerDTO>>> getAll() {
        Response<List<BuyerDTO>> response = new Response<>();
        ResError error = new ResError();
        List<BuyerDTO> buyerDTOList = queryFacade.getAll();
        response.setData(buyerDTOList);

        if (Objects.isNull(buyerDTOList)) {
            return utils.getBadResponseEntity(response, error, HttpStatus.NOT_FOUND, "Buyers not found");
        } else {
            return utils.getResponseEntityOk(response, error);
        }
    }

    @Override
    public ResponseEntity<Response<Boolean>> updateBuyer(RequestCreateBuyerDTO buyerDTO) {
        Response<Boolean> response = new Response<>();
        ResError error = new ResError();
        Boolean updated = commandFacade.updateBuyer(buyerDTO);
        response.setData(updated);

        if (updated) {
            return utils.getResponseEntityOk(response, error);
        } else {
            return utils.getBadResponseEntity(response, error, HttpStatus.NOT_FOUND, "Buyers not updated");
        }
    }
}

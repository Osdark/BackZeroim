package com.zeroim.admin.adapters.bill;

import com.zeroim.admin.adapters.util.ControllerUtils;
import com.zeroim.admin.facades.command.bill.BillCommandFacade;
import com.zeroim.admin.facades.query.bill.BillQueryFacade;
import com.zeroim.admin.operations.bill.BillOperations;
import com.zeroim.admin.ports.primary.bill.BillController;
import com.zeroim.admin.requests.bill.BillDTO;
import com.zeroim.admin.requests.bill.CreateBillDTO;
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
public class DefaultBillController implements BillController {
    @Autowired
    private final BillCommandFacade commandFacade;
    @Autowired
    private final BillOperations operations;
    @Autowired
    private final BillQueryFacade queryFacade;
    @Autowired
    private final ControllerUtils utils;

    public DefaultBillController(BillCommandFacade commandFacade, BillOperations operations,
                                 BillQueryFacade queryFacade, ControllerUtils utils) {
        this.commandFacade = commandFacade;
        this.operations = operations;
        this.queryFacade = queryFacade;
        this.utils = utils;
    }

    @Override
    public ResponseEntity<Response<BillDTO>> create(CreateBillDTO createBillDTO) {
        Response<BillDTO> response = new Response<>();
        ResError error = new ResError();
        BillDTO billDTO = operations.createBill(createBillDTO);
        response.setData(billDTO);

        if (Objects.isNull(billDTO)) {
            return utils.getBadResponseEntity(response, error, HttpStatus.INTERNAL_SERVER_ERROR, "Bill not created");
        } else {
            return utils.getResponseEntityOk(response, error);
        }
    }


    @Override
    public ResponseEntity<Response<List<BillDTO>>> getAll() {
        Response<List<BillDTO>> response = new Response<>();
        ResError error = new ResError();
        List<BillDTO> billDTOS = queryFacade.getAll();
        response.setData(billDTOS);

        if (Objects.isNull(billDTOS)) {
            return utils.getBadResponseEntity(response, error, HttpStatus.NOT_FOUND, "List not found");
        } else {
            return utils.getResponseEntityOk(response, error);
        }
    }

    @Override
    public ResponseEntity<Response<BillDTO>> getById(UUID id) {
        Response<BillDTO> response = new Response<>();
        ResError error = new ResError();
        BillDTO billDTO = queryFacade.getById(id);
        response.setData(billDTO);

        if (Objects.isNull(billDTO)) {
            return utils.getBadResponseEntity(response, error, HttpStatus.NOT_FOUND, "List not found");
        } else {
            return utils.getResponseEntityOk(response, error);
        }
    }

    @Override
    public ResponseEntity<Response<Boolean>> payBill(UUID id) {
        Response<Boolean> response = new Response<>();
        ResError error = new ResError();
        Boolean billPaidOut = commandFacade.payBill(id);
        response.setData(billPaidOut);

        if (Objects.equals(billPaidOut, false)) {
            return utils.getBadResponseEntity(response, error, HttpStatus.NOT_FOUND, "Bill couldn't be paid");
        } else {
            return utils.getResponseEntityOk(response, error);
        }
    }
}

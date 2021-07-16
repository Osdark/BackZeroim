package com.zeroim.admin.facades.command.bill;

import com.zeroim.admin.ports.primary.bill.BillDetailService;
import com.zeroim.admin.ports.primary.bill.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DefaultBillCommandFacade implements BillCommandFacade {
    @Autowired
    private final BillService service;

    public DefaultBillCommandFacade(BillService service) {
        this.service = service;
    }

    @Override
    public Boolean payBill(UUID id) {
        return service.payBill(id);
    }
}

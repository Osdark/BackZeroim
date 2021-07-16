package com.zeroim.admin.facades.query.bill;

import com.zeroim.admin.converter.Converter;
import com.zeroim.admin.domain.bill.Bill;
import com.zeroim.admin.populators.Populator;
import com.zeroim.admin.populators.bill.BillResponsePopulator;
import com.zeroim.admin.ports.primary.bill.BillService;
import com.zeroim.admin.requests.bill.BillDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class DefaultBillQueryFacade implements BillQueryFacade {
    @Autowired
    private final BillService service;

    public DefaultBillQueryFacade(BillService service) {
        this.service = service;
    }

    @Override
    public BillDTO getById(UUID id) {
        return convertBill(service.getById(id));
    }

    @Override
    public List<BillDTO> getAll() {
        return convertBills(service.getAll());
    }

    private List<BillDTO> convertBills(List<Bill> bills) {
        Converter<Bill, BillDTO> responseConverter = buildResponseConverter();
        return responseConverter.convertAll(bills);
    }

    private BillDTO convertBill(Bill bill) {
        Converter<Bill, BillDTO> responseConverter = buildResponseConverter();
        return responseConverter.convert(bill);
    }

    private Converter<Bill, BillDTO> buildResponseConverter() {
        Populator<Bill, BillDTO> responsePopulator = new BillResponsePopulator();
        return Converter.of(BillDTO.class).withPopulator(responsePopulator);
    }
}

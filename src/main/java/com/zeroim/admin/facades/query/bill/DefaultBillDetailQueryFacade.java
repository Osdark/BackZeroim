package com.zeroim.admin.facades.query.bill;

import com.zeroim.admin.converter.Converter;
import com.zeroim.admin.domain.bill.BillDetail;
import com.zeroim.admin.populators.Populator;
import com.zeroim.admin.populators.bill.BillDetailResponsePopulator;
import com.zeroim.admin.ports.primary.bill.BillDetailService;
import com.zeroim.admin.requests.bill.BillDetailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DefaultBillDetailQueryFacade implements BillDetailQueryFacade {
    @Autowired
    private final BillDetailService service;

    public DefaultBillDetailQueryFacade(BillDetailService service) {
        this.service = service;
    }

    @Override
    public BillDetailDTO getById(UUID id) {
        return convertBillDetail(service.getById(id));
    }

    private BillDetailDTO convertBillDetail(BillDetail billDetail) {
        Converter<BillDetail, BillDetailDTO> responseConverter = buildResponseConverter();
        return responseConverter.convert(billDetail);
    }

    private Converter<BillDetail, BillDetailDTO> buildResponseConverter() {
        Populator<BillDetail, BillDetailDTO> responsePopulator = new BillDetailResponsePopulator();
        return Converter.of(BillDetailDTO.class).withPopulator(responsePopulator);
    }
}

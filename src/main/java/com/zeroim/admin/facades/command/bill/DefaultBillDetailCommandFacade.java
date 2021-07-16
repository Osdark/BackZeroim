package com.zeroim.admin.facades.command.bill;

import com.zeroim.admin.converter.Converter;
import com.zeroim.admin.domain.bill.BillDetail;
import com.zeroim.admin.populators.Populator;
import com.zeroim.admin.populators.bill.BillDetailRequestPopulator;
import com.zeroim.admin.populators.bill.BillDetailResponsePopulator;
import com.zeroim.admin.ports.primary.bill.BillDetailService;
import com.zeroim.admin.requests.bill.BillDetailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultBillDetailCommandFacade implements BillDetailCommandFacade {
    @Autowired
    private final BillDetailService service;

    public DefaultBillDetailCommandFacade(BillDetailService service) {
        this.service = service;
    }

    @Override
    public BillDetailDTO create(BillDetailDTO billDetailDTO) {
        return convertBillDetail(service.create(convertBillDetailDTO(billDetailDTO)));
    }

    @Override
    public List<BillDetailDTO> massiveCreation(List<BillDetailDTO> billDetails) {
        return null;
    }

    private BillDetailDTO convertBillDetail(BillDetail billDetail) {
        Converter<BillDetail, BillDetailDTO> responseConverter = buildResponseConverter();
        return responseConverter.convert(billDetail);
    }

    private Converter<BillDetail, BillDetailDTO> buildResponseConverter() {
        Populator<BillDetail, BillDetailDTO> responsePopulator = new BillDetailResponsePopulator();
        return Converter.of(BillDetailDTO.class).withPopulator(responsePopulator);
    }

    private BillDetail convertBillDetailDTO(BillDetailDTO billDetailDTO) {
        Converter<BillDetailDTO, BillDetail> requestConverter = buildRequestConverter();
        return requestConverter.convert(billDetailDTO);
    }

    private Converter<BillDetailDTO, BillDetail> buildRequestConverter() {
        Populator<BillDetailDTO, BillDetail> requestPopulator = new BillDetailRequestPopulator();
        return Converter.of(BillDetail.class).withPopulator(requestPopulator);
    }

}

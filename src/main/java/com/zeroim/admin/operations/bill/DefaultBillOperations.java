package com.zeroim.admin.operations.bill;

import com.zeroim.admin.converter.Converter;
import com.zeroim.admin.domain.bill.Bill;
import com.zeroim.admin.domain.bill.BillDetail;
import com.zeroim.admin.populators.Populator;
import com.zeroim.admin.populators.bill.BillDetailRequestPopulator;
import com.zeroim.admin.populators.bill.BillResponsePopulator;
import com.zeroim.admin.populators.bill.NewBillPopulator;
import com.zeroim.admin.ports.primary.bill.BillDetailService;
import com.zeroim.admin.ports.primary.bill.BillService;
import com.zeroim.admin.requests.bill.BillDTO;
import com.zeroim.admin.requests.bill.BillDetailDTO;
import com.zeroim.admin.requests.bill.CreateBillDTO;
import com.zeroim.admin.requests.bill.NewBillDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class DefaultBillOperations implements BillOperations {
    @Autowired
    private final BillService billService;
    @Autowired
    private final BillDetailService billDetailService;

    public DefaultBillOperations(BillService billService, BillDetailService billDetailService) {
        this.billService = billService;
        this.billDetailService = billDetailService;
    }

    @Override
    public BillDTO createBill(CreateBillDTO billDTO) {
        List<BillDetail> billDetailDTOList = convertBillDetailList(billDTO.getBillDetailDTOS());
        billDetailDTOList.forEach(billDetail -> billDetail.setId(UUID.randomUUID()));
        billDetailDTOList = billDetailService.massiveCreation(billDetailDTOList);
        Bill billToSave = getNewBillDTO(billDTO.getBillDTO());
        UUID[] billDetailIds = new UUID[billDetailDTOList.size()];
        for (int i = 0; i < billDetailDTOList.size(); i++) {
            billDetailIds[i] = billDetailDTOList.get(i).getId();
        }
        billToSave.setBillDetail(billDetailIds);
        billToSave = billService.create(billToSave);
        return convertBill(billToSave);
    }

    private Bill getNewBillDTO(NewBillDTO newBillDTO) {
        Converter<NewBillDTO, Bill> newBillConverter = buildNewBillConverter();
        return newBillConverter.convert(newBillDTO);
    }

    private Converter<NewBillDTO, Bill> buildNewBillConverter() {
        Populator<NewBillDTO, Bill> newBillPopulator = new NewBillPopulator();
        return Converter.of(Bill.class).withPopulator(newBillPopulator);
    }

    private BillDTO convertBill(Bill bill) {
        Converter<Bill, BillDTO> responseConverter = buildBillResponseConverter();
        return responseConverter.convert(bill);
    }

    private Converter<Bill, BillDTO> buildBillResponseConverter() {
        Populator<Bill, BillDTO> responsePopulator = new BillResponsePopulator();
        return Converter.of(BillDTO.class).withPopulator(responsePopulator);
    }

    private List<BillDetail> convertBillDetailList(List<BillDetailDTO> billDetailDTOS) {
        Converter<BillDetailDTO, BillDetail> requestConverter = buildBillDetailRequestConverter();
        return requestConverter.convertAll(billDetailDTOS);
    }

    private Converter<BillDetailDTO, BillDetail> buildBillDetailRequestConverter() {
        Populator<BillDetailDTO, BillDetail> requestPopulator = new BillDetailRequestPopulator();
        return Converter.of(BillDetail.class).withPopulator(requestPopulator);
    }
}

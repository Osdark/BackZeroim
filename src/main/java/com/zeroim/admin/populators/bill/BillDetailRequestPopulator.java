package com.zeroim.admin.populators.bill;

import com.zeroim.admin.domain.bill.BillDetail;
import com.zeroim.admin.populators.Populator;
import com.zeroim.admin.requests.bill.BillDetailDTO;

public class BillDetailRequestPopulator implements Populator<BillDetailDTO, BillDetail> {
    @Override
    public void populate(BillDetailDTO billDetailDTO, BillDetail billDetail) {
        billDetail.setId(billDetailDTO.getId());
        billDetail.setQuantity(billDetailDTO.getQuantity());
        billDetail.setProductId(billDetailDTO.getProductId());
        billDetail.setValue(billDetailDTO.getValue());
    }
}

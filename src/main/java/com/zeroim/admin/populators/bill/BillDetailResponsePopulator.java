package com.zeroim.admin.populators.bill;

import com.zeroim.admin.domain.bill.BillDetail;
import com.zeroim.admin.populators.Populator;
import com.zeroim.admin.requests.bill.BillDetailDTO;

public class BillDetailResponsePopulator implements Populator<BillDetail, BillDetailDTO> {
    @Override
    public void populate(BillDetail billDetail, BillDetailDTO billDetailDTO) {
        billDetailDTO.setId(billDetail.getId());
        billDetailDTO.setQuantity(billDetail.getQuantity());
        billDetailDTO.setProductId(billDetail.getProductId());
        billDetailDTO.setValue(billDetail.getValue());
    }
}

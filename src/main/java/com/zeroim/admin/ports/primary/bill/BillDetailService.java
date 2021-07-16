package com.zeroim.admin.ports.primary.bill;

import com.zeroim.admin.domain.bill.BillDetail;

import java.util.List;
import java.util.UUID;

public interface BillDetailService {
    BillDetail getById(UUID id);

    BillDetail create(BillDetail billDetail);

    List<BillDetail> massiveCreation(List<BillDetail> billDetailDTOList);
}

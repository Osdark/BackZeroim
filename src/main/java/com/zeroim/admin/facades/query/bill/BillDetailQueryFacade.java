package com.zeroim.admin.facades.query.bill;

import com.zeroim.admin.requests.bill.BillDetailDTO;

import java.util.UUID;

public interface BillDetailQueryFacade {
    BillDetailDTO getById(UUID id);
}

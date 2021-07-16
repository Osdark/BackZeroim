package com.zeroim.admin.populators.bill;

import com.zeroim.admin.domain.bill.Bill;
import com.zeroim.admin.populators.Populator;
import com.zeroim.admin.requests.bill.NewBillDTO;

import java.util.UUID;

public class NewBillPopulator implements Populator<NewBillDTO, Bill> {
    @Override
    public void populate(NewBillDTO newBillDTO, Bill bill) {
        bill.setId(UUID.randomUUID());
        bill.setBillNumber(newBillDTO.getBillNumber());
        bill.setDate(newBillDTO.getDate());
        bill.setClientId(newBillDTO.getClientId());
        bill.setTotal(newBillDTO.getTotal());
        bill.setPaidOut(false);
    }
}

package com.zeroim.admin.populators.bill;

import com.zeroim.admin.domain.bill.Bill;
import com.zeroim.admin.populators.Populator;
import com.zeroim.admin.requests.bill.BillDTO;

import java.util.Arrays;
import java.util.stream.Collectors;

public class BillResponsePopulator implements Populator<Bill, BillDTO> {
    @Override
    public void populate(Bill bill, BillDTO billDTO) {
        billDTO.setId(bill.getId());
        billDTO.setBillNumber(bill.getBillNumber());
        billDTO.setBillDetail(Arrays.stream(bill.getBillDetail()).collect(Collectors.toList()));
        billDTO.setClientId(bill.getClientId());
        billDTO.setDate(bill.getDate());
        billDTO.setTotal(bill.getTotal());
        billDTO.setPaidOut(bill.isPaidOut());
    }
}

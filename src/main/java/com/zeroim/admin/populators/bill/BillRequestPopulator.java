package com.zeroim.admin.populators.bill;

import com.zeroim.admin.domain.bill.Bill;
import com.zeroim.admin.populators.Populator;
import com.zeroim.admin.requests.bill.BillDTO;

import java.util.UUID;

public class BillRequestPopulator implements Populator<BillDTO, Bill> {
    @Override
    public void populate(BillDTO billDTO, Bill bill) {
        bill.setId(billDTO.getId());
        bill.setBillNumber(billDTO.getBillNumber());
        bill.setBillDetail((UUID[]) billDTO.getBillDetail().toArray());
        bill.setClientId(billDTO.getClientId());
        bill.setDate(billDTO.getDate());
        bill.setTotal(billDTO.getTotal());
        bill.setPaidOut(billDTO.isPaidOut());
    }
}

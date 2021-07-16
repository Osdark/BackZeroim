package com.zeroim.admin.operations.bill;

import com.zeroim.admin.requests.bill.BillDTO;
import com.zeroim.admin.requests.bill.CreateBillDTO;

public interface BillOperations {
    BillDTO createBill(CreateBillDTO billDTO);
}

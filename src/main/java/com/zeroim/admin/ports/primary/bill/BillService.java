package com.zeroim.admin.ports.primary.bill;

import com.zeroim.admin.domain.bill.Bill;

import java.util.List;
import java.util.UUID;

public interface BillService {
    Bill create(Bill bill);

    List<Bill> getAll();

    Bill getById(UUID id);

    Boolean payBill(UUID id);
}

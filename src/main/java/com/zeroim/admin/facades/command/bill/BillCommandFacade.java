package com.zeroim.admin.facades.command.bill;

import java.util.UUID;

public interface BillCommandFacade {
    Boolean payBill(UUID id);
}

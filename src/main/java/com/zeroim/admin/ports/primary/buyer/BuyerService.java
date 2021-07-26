package com.zeroim.admin.ports.primary.buyer;

import com.zeroim.admin.domain.buyer.Buyer;

import java.util.List;
import java.util.UUID;

public interface BuyerService {
    Buyer create(Buyer buyer);

    Buyer getById(UUID id);

    List<Buyer> getAll();

    Boolean updateBuyer(Buyer buyer, UUID id);
}

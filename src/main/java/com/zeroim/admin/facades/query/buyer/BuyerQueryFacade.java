package com.zeroim.admin.facades.query.buyer;

import com.zeroim.admin.requests.buyer.BuyerDTO;

import java.util.List;
import java.util.UUID;

public interface BuyerQueryFacade {
    List<BuyerDTO> getAll();

    BuyerDTO getById(UUID id);
}

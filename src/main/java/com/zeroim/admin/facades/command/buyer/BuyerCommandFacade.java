package com.zeroim.admin.facades.command.buyer;

import com.zeroim.admin.requests.buyer.BuyerDTO;
import com.zeroim.admin.requests.buyer.RequestCreateBuyerDTO;

import java.util.UUID;

public interface BuyerCommandFacade {
    BuyerDTO create(RequestCreateBuyerDTO buyerDTO);

    Boolean updateBuyer(RequestCreateBuyerDTO buyerDTO, UUID id);
}

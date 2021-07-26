package com.zeroim.admin.facades.command.buyer;

import com.zeroim.admin.requests.buyer.BuyerDTO;
import com.zeroim.admin.requests.buyer.RequestCreateBuyerDTO;

public interface BuyerCommandFacade {
    BuyerDTO create(RequestCreateBuyerDTO buyerDTO);

    Boolean updateBuyer(RequestCreateBuyerDTO buyerDTO);
}

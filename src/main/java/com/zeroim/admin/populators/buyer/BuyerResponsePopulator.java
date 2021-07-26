package com.zeroim.admin.populators.buyer;

import com.zeroim.admin.domain.buyer.Buyer;
import com.zeroim.admin.populators.Populator;
import com.zeroim.admin.requests.buyer.BuyerDTO;

public class BuyerResponsePopulator implements Populator<Buyer, BuyerDTO> {
    @Override
    public void populate(Buyer buyer, BuyerDTO buyerDTO) {
        buyerDTO.setId(buyer.getId());
        buyerDTO.setName(buyer.getName());
    }
}

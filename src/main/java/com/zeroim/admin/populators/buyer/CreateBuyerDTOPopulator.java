package com.zeroim.admin.populators.buyer;

import com.zeroim.admin.domain.buyer.Buyer;
import com.zeroim.admin.populators.Populator;
import com.zeroim.admin.requests.buyer.RequestCreateBuyerDTO;

import java.util.UUID;

public class CreateBuyerDTOPopulator implements Populator<RequestCreateBuyerDTO, Buyer> {
    @Override
    public void populate(RequestCreateBuyerDTO buyerDTO, Buyer buyer) {
        buyer.setId(UUID.randomUUID());
        buyer.setAddress(buyerDTO.getAddress());
        buyer.setIdNumber(buyerDTO.getIdNumber());
        buyer.setIdType(buyerDTO.getIdType());
        buyer.setName(buyerDTO.getName());
        buyer.setPhone(buyerDTO.getPhone());
    }
}

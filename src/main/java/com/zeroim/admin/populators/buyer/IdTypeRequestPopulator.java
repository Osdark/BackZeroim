package com.zeroim.admin.populators.buyer;

import com.zeroim.admin.domain.buyer.IdType;
import com.zeroim.admin.populators.Populator;
import com.zeroim.admin.requests.buyer.IdTypeDTO;

import java.util.UUID;

public class IdTypeRequestPopulator implements Populator<IdTypeDTO, IdType> {
    @Override
    public void populate(IdTypeDTO idTypeDTO, IdType idType) {
        idType.setId(UUID.randomUUID());
        idType.setAbbreviation(idTypeDTO.getAbbreviation());
        idType.setName(idTypeDTO.getName());
    }
}

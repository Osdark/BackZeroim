package com.zeroim.admin.populators.buyer;

import com.zeroim.admin.domain.buyer.IdType;
import com.zeroim.admin.populators.Populator;
import com.zeroim.admin.requests.buyer.IdTypeDTO;

public class IdTypeResponsePopulator implements Populator<IdType, IdTypeDTO> {
    @Override
    public void populate(IdType idType, IdTypeDTO idTypeDTO) {
        idTypeDTO.setId(idType.getId());
        idTypeDTO.setAbbreviation(idType.getAbbreviation());
        idTypeDTO.setName(idType.getName());
    }
}

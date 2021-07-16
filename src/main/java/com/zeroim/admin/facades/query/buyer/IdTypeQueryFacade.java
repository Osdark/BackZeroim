package com.zeroim.admin.facades.query.buyer;

import com.zeroim.admin.requests.buyer.IdTypeDTO;

import java.util.List;
import java.util.UUID;

public interface IdTypeQueryFacade {
    List<IdTypeDTO> getAll();

    IdTypeDTO getById(UUID id);
}

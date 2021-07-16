package com.zeroim.admin.facades.command.buyer;

import com.zeroim.admin.requests.buyer.IdTypeDTO;

import java.util.UUID;

public interface IdTypeCommandFacade {
    IdTypeDTO create(IdTypeDTO idTypeDTO);

    int delete(UUID id);
}

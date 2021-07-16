package com.zeroim.admin.ports.primary.buyer;

import com.zeroim.admin.domain.buyer.IdType;

import java.util.List;
import java.util.UUID;

public interface IdTypeService {
    IdType create(IdType idType);

    List<IdType> getAll();

    IdType getById(UUID id);

    int delete(UUID id);
}

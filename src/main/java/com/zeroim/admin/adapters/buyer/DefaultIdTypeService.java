package com.zeroim.admin.adapters.buyer;

import com.zeroim.admin.domain.buyer.IdType;
import com.zeroim.admin.ports.primary.buyer.IdTypeService;
import com.zeroim.admin.ports.secondary.buyer.IdTypeRepo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class DefaultIdTypeService implements IdTypeService {
    private final IdTypeRepo repo;

    public DefaultIdTypeService(IdTypeRepo repo) {
        this.repo = repo;
    }

    @Override
    public IdType create(IdType idType) {
        return repo.save(idType);
    }

    @Override
    public List<IdType> getAll() {
        Iterable<IdType> idTypeIterable = repo.findAll();
        return StreamSupport.stream(idTypeIterable.spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public IdType getById(UUID id) {
        return repo.findById(id).get();
    }

    @Override
    public int delete(UUID id) {
        repo.deleteById(id);
        Optional<IdType> idType = repo.findById(id);
        if (idType.isPresent()) {
            return 1;
        }
        return 0;
    }
}

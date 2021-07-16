package com.zeroim.admin.ports.secondary.buyer;

import com.zeroim.admin.domain.buyer.IdType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IdTypeRepo extends CrudRepository<IdType, UUID> {
}

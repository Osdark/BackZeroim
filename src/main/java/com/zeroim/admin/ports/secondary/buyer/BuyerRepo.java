package com.zeroim.admin.ports.secondary.buyer;

import com.zeroim.admin.domain.buyer.Buyer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BuyerRepo extends CrudRepository<Buyer, UUID> {
}

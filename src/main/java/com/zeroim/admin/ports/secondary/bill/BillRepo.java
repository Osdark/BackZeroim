package com.zeroim.admin.ports.secondary.bill;

import com.zeroim.admin.domain.bill.Bill;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BillRepo extends CrudRepository<Bill, UUID> {
}

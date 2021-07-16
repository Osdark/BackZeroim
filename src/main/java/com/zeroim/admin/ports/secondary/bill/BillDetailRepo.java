package com.zeroim.admin.ports.secondary.bill;

import com.zeroim.admin.domain.bill.BillDetail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BillDetailRepo extends CrudRepository<BillDetail, UUID> {
}

package com.zeroim.admin.adapters.bill;

import com.zeroim.admin.domain.bill.BillDetail;
import com.zeroim.admin.ports.primary.bill.BillDetailService;
import com.zeroim.admin.ports.secondary.bill.BillDetailRepo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class DefaultBillDetailService implements BillDetailService {
    private final BillDetailRepo repo;

    public DefaultBillDetailService(BillDetailRepo repo) {
        this.repo = repo;
    }

    @Override
    public BillDetail getById(UUID id) {
        return repo.findById(id).get();
    }

    @Override
    public BillDetail create(BillDetail billDetail) {
        return repo.save(billDetail);
    }

    @Override
    public List<BillDetail> massiveCreation(List<BillDetail> billDetailDTOList) {
        return billDetailDTOList.stream().map(repo::save).collect(Collectors.toList());
    }
}

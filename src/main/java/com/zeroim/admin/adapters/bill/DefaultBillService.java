package com.zeroim.admin.adapters.bill;

import com.zeroim.admin.domain.bill.Bill;
import com.zeroim.admin.ports.primary.bill.BillService;
import com.zeroim.admin.ports.secondary.bill.BillRepo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class DefaultBillService implements BillService {
    private final BillRepo repo;

    public DefaultBillService(BillRepo repo) {
        this.repo = repo;
    }

    @Override
    public Bill create(Bill bill) {
        return repo.save(bill);
    }

    @Override
    public List<Bill> getAll() {
        Iterable<Bill> bills = repo.findAll();
        return StreamSupport.stream(bills.spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Bill getById(UUID id) {
        return repo.findById(id).get();
    }
}

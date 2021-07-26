package com.zeroim.admin.adapters.buyer;

import com.zeroim.admin.domain.buyer.Buyer;
import com.zeroim.admin.domain.buyer.IdType;
import com.zeroim.admin.ports.primary.buyer.BuyerService;
import com.zeroim.admin.ports.secondary.buyer.BuyerRepo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class DefaultBuyerService implements BuyerService {
    private final BuyerRepo repo;

    public DefaultBuyerService(BuyerRepo repo) {
        this.repo = repo;
    }

    @Override
    public Buyer create(Buyer buyer) {
        return repo.save(buyer);
    }

    @Override
    public Buyer getById(UUID id) {
        return repo.findById(id).get();
    }

    @Override
    public List<Buyer> getAll() {
        Iterable<Buyer> buyers = repo.findAll();
        return StreamSupport.stream(buyers.spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Boolean updateBuyer(Buyer buyer) {
        Optional<Buyer> buyerDB = repo.findById(buyer.getId());
        if (buyerDB.isPresent()) {
            repo.deleteById(buyer.getId());
            repo.save(buyer);
            return true;
        }
        return false;
    }
}

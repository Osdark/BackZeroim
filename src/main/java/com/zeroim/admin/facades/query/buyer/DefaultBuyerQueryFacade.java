package com.zeroim.admin.facades.query.buyer;

import com.zeroim.admin.converter.Converter;
import com.zeroim.admin.domain.buyer.Buyer;
import com.zeroim.admin.populators.Populator;
import com.zeroim.admin.populators.buyer.BuyerResponsePopulator;
import com.zeroim.admin.ports.primary.buyer.BuyerService;
import com.zeroim.admin.requests.buyer.BuyerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class DefaultBuyerQueryFacade implements BuyerQueryFacade {
    @Autowired
    private final BuyerService service;

    public DefaultBuyerQueryFacade(BuyerService service) {
        this.service = service;
    }

    @Override
    public List<BuyerDTO> getAll() {
        return convertBuyerList(service.getAll());
    }

    @Override
    public BuyerDTO getById(UUID id) {
        return convertBuyer(service.getById(id));
    }

    private BuyerDTO convertBuyer(Buyer buyer) {
        Converter<Buyer, BuyerDTO> responseConverter = buildResponseConverter();
        return responseConverter.convert(buyer);
    }

    private List<BuyerDTO> convertBuyerList(List<Buyer> buyers) {
        Converter<Buyer, BuyerDTO> responseConverter = buildResponseConverter();
        return responseConverter.convertAll(buyers);
    }

    private Converter<Buyer, BuyerDTO> buildResponseConverter() {
        Populator<Buyer, BuyerDTO> responsePopulator = new BuyerResponsePopulator();
        return Converter.of(BuyerDTO.class).withPopulator(responsePopulator);
    }
}

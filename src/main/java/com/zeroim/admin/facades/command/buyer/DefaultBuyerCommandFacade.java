package com.zeroim.admin.facades.command.buyer;

import com.zeroim.admin.converter.Converter;
import com.zeroim.admin.domain.buyer.Buyer;
import com.zeroim.admin.populators.Populator;
import com.zeroim.admin.populators.buyer.BuyerResponsePopulator;
import com.zeroim.admin.populators.buyer.CreateBuyerDTOPopulator;
import com.zeroim.admin.ports.primary.buyer.BuyerService;
import com.zeroim.admin.requests.buyer.BuyerDTO;
import com.zeroim.admin.requests.buyer.RequestCreateBuyerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DefaultBuyerCommandFacade implements BuyerCommandFacade {
    @Autowired
    private final BuyerService service;

    public DefaultBuyerCommandFacade(BuyerService service) {
        this.service = service;
    }

    @Override
    public BuyerDTO create(RequestCreateBuyerDTO buyerDTO) {
        return convertBuyer(service.create(convertCreateBuyerDTO(buyerDTO)));
    }

    @Override
    public Boolean updateBuyer(RequestCreateBuyerDTO buyerDTO, UUID id) {
        return service.updateBuyer(convertCreateBuyerDTO(buyerDTO), id);
    }

    private BuyerDTO convertBuyer(Buyer buyer) {
        Converter<Buyer, BuyerDTO> responseConverter = buildResponseConverter();
        return responseConverter.convert(buyer);
    }

    private Converter<Buyer, BuyerDTO> buildResponseConverter() {
        Populator<Buyer, BuyerDTO> responsePopulator = new BuyerResponsePopulator();
        return Converter.of(BuyerDTO.class).withPopulator(responsePopulator);
    }

    private Buyer convertCreateBuyerDTO(RequestCreateBuyerDTO buyerDTO) {
        Converter<RequestCreateBuyerDTO, Buyer> converterCreateBuyerDTO = buildConverterCreateBuyerDTO();
        return converterCreateBuyerDTO.convert(buyerDTO);
    }

    private Converter<RequestCreateBuyerDTO, Buyer> buildConverterCreateBuyerDTO() {
        Populator<RequestCreateBuyerDTO, Buyer> createBuyerDTOBuyerPopulator = new CreateBuyerDTOPopulator();
        return Converter.of(Buyer.class).withPopulator(createBuyerDTOBuyerPopulator);
    }
}

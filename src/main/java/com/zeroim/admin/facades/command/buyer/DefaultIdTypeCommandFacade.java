package com.zeroim.admin.facades.command.buyer;

import com.zeroim.admin.converter.Converter;
import com.zeroim.admin.domain.buyer.IdType;
import com.zeroim.admin.populators.Populator;
import com.zeroim.admin.populators.buyer.IdTypeRequestPopulator;
import com.zeroim.admin.populators.buyer.IdTypeResponsePopulator;
import com.zeroim.admin.ports.primary.buyer.IdTypeService;
import com.zeroim.admin.requests.buyer.IdTypeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DefaultIdTypeCommandFacade implements IdTypeCommandFacade {
    @Autowired
    private final IdTypeService service;

    public DefaultIdTypeCommandFacade(IdTypeService service) {
        this.service = service;
    }

    @Override
    public IdTypeDTO create(IdTypeDTO idTypeDTO) {
        return convertIdType(service.create(convertIdTypeDTO(idTypeDTO)));
    }

    @Override
    public int delete(UUID id) {
        return service.delete(id);
    }

    private IdTypeDTO convertIdType(IdType idType) {
        Converter<IdType, IdTypeDTO> responseConverter = buildResponseConverter();
        return responseConverter.convert(idType);
    }

    private Converter<IdType, IdTypeDTO> buildResponseConverter() {
        Populator<IdType, IdTypeDTO> responsePopulator = new IdTypeResponsePopulator();
        return Converter.of(IdTypeDTO.class).withPopulator(responsePopulator);
    }

    private IdType convertIdTypeDTO(IdTypeDTO idTypeDTO) {
        Converter<IdTypeDTO, IdType> requestConverter = buildRequestConverter();
        return requestConverter.convert(idTypeDTO);
    }

    private Converter<IdTypeDTO, IdType> buildRequestConverter() {
        Populator<IdTypeDTO, IdType> requestPopulator = new IdTypeRequestPopulator();
        return Converter.of(IdType.class).withPopulator(requestPopulator);
    }
}

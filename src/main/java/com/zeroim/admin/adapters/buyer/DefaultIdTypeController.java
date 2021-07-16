package com.zeroim.admin.adapters.buyer;

import com.zeroim.admin.facades.command.buyer.IdTypeCommandFacade;
import com.zeroim.admin.facades.query.buyer.IdTypeQueryFacade;
import com.zeroim.admin.ports.primary.buyer.IdTypeController;
import com.zeroim.admin.requests.buyer.IdTypeDTO;
import com.zeroim.admin.requests.util.ResError;
import com.zeroim.admin.requests.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
public class DefaultIdTypeController implements IdTypeController {
    @Autowired
    private final IdTypeCommandFacade commandFacade;
    @Autowired
    private final IdTypeQueryFacade queryFacade;

    public DefaultIdTypeController(IdTypeCommandFacade commandFacade, IdTypeQueryFacade queryFacade) {
        this.commandFacade = commandFacade;
        this.queryFacade = queryFacade;
    }

    @Override
    public ResponseEntity<Response<IdTypeDTO>> create(IdTypeDTO idTypeDTO) {
        Response<IdTypeDTO> response = new Response<>();
        ResError error = new ResError();
        IdTypeDTO idTypeCreated = commandFacade.create(idTypeDTO);
        response.setData(idTypeCreated);

        if (Objects.isNull(idTypeCreated)) {
            return getBadResponseEntity(response, error, HttpStatus.INTERNAL_SERVER_ERROR, "IdType not created");
        } else {
            return getResponseEntityOk(response, error);
        }
    }

    @Override
    public ResponseEntity<Response<List<IdTypeDTO>>> getAll() {
        Response<List<IdTypeDTO>> response = new Response<>();
        ResError error = new ResError();
        List<IdTypeDTO> idTypeDTOList = queryFacade.getAll();
        response.setData(idTypeDTOList);

        if (Objects.isNull(idTypeDTOList)) {
            return getBadResponseEntity(response, error, HttpStatus.NOT_FOUND, "IdType list not found");
        } else {
            return getResponseEntityOk(response, error);
        }
    }

    @Override
    public ResponseEntity<Response<IdTypeDTO>> getById(UUID id) {
        Response<IdTypeDTO> response = new Response<>();
        ResError error = new ResError();
        IdTypeDTO idTypeDTO = queryFacade.getById(id);
        response.setData(idTypeDTO);

        if (Objects.isNull(idTypeDTO)) {
            return getBadResponseEntity(response, error, HttpStatus.NOT_FOUND, "IdType not found");
        } else {
            return getResponseEntityOk(response, error);
        }
    }

    @Override
    public ResponseEntity<Response<Integer>> delete(UUID id) {
        Response<Integer> response = new Response<>();
        ResError error = new ResError();
        Integer deleted = commandFacade.delete(id);
        response.setData(deleted);

        if (Objects.equals(deleted, 0)) {
            return getBadResponseEntity(response, error, HttpStatus.INTERNAL_SERVER_ERROR, "IdType not deleted");
        } else {
            return getResponseEntityOk(response, error);
        }
    }

    private <T> ResponseEntity<Response<T>> getResponseEntityOk(Response<T> response, ResError error) {
        response.setError(error);
        return ResponseEntity.ok().body(response);
    }

    private <T> ResponseEntity<Response<T>> getBadResponseEntity(Response<T> response, ResError error,
                                                                 HttpStatus status, String message) {
        error.setErrorCode(status.value());
        error.setMessage(message);
        response.setError(error);
        return ResponseEntity.status(status).build();
    }
}

package com.zeroim.admin.adapters.admin;

import com.zeroim.admin.facades.command.admin.AdminCommandFacade;
import com.zeroim.admin.facades.query.admin.AdminQueryFacade;
import com.zeroim.admin.ports.primary.admin.AdminController;
import com.zeroim.admin.requests.admin.AdminDTO;
import com.zeroim.admin.requests.admin.RequestAdminLoginDTO;
import com.zeroim.admin.requests.admin.RequestUpdatePasswordDTO;
import com.zeroim.admin.requests.util.ResError;
import com.zeroim.admin.requests.util.Response;
import com.zeroim.admin.util.BussinessExceptions.InvalidPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class DefaultAdminController implements AdminController {
    @Autowired
    private final AdminCommandFacade commandFacade;
    @Autowired
    private final AdminQueryFacade queryFacade;

    public DefaultAdminController(AdminCommandFacade commandFacade, AdminQueryFacade queryFacade) {
        this.commandFacade = commandFacade;
        this.queryFacade = queryFacade;
    }

    @Override
    public ResponseEntity<Response<AdminDTO>> create(AdminDTO adminDTO) {
        Response<AdminDTO> response = new Response<>();
        ResError error = new ResError();
        adminDTO = commandFacade.create(adminDTO);
        response.setData(adminDTO);

        if (Objects.isNull(adminDTO)) {
            return getBadResponseEntity(response, error, "Admin not created");
        } else {
            return getResponseEntityOk(response, error);
        }
    }

    @Override
    public ResponseEntity<Response<AdminDTO>> login(RequestAdminLoginDTO adminLoginDTO) {
        Response<AdminDTO> response = new Response<>();
        ResError error = new ResError();
        AdminDTO adminDTO = commandFacade.login(adminLoginDTO);
        response.setData(adminDTO);

        if (Objects.isNull(adminDTO)) {
            return getBadResponseEntity(response, error, "Admin not logged in");
        } else {
            return getResponseEntityOk(response, error);
        }
    }

    @Override
    public ResponseEntity<Response<Boolean>> updatePassword(RequestUpdatePasswordDTO updatePasswordDTO)
            throws InvalidPasswordException {
        Response<Boolean> response = new Response<>();
        ResError error = new ResError();
        Boolean passwordUpdated = commandFacade.updatePassword(updatePasswordDTO);
        response.setData(passwordUpdated);

        if (passwordUpdated) {
            return getResponseEntityOk(response, error);
        } else {
            return getBadResponseEntity(response, error, "Admin password not updated");
        }
    }

    private <T> ResponseEntity<Response<T>> getResponseEntityOk(Response<T> response, ResError error) {
        response.setError(error);
        return ResponseEntity.ok().body(response);
    }

    private <T> ResponseEntity<Response<T>> getBadResponseEntity(Response<T> response, ResError error,
                                                                 String message) {
        error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage(message);
        response.setError(error);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}

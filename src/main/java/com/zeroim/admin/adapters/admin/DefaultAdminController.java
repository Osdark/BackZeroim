package com.zeroim.admin.adapters.admin;

import com.zeroim.admin.adapters.util.ControllerUtils;
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
import java.util.UUID;

@RestController
public class DefaultAdminController implements AdminController {
    @Autowired
    private final AdminCommandFacade commandFacade;
    @Autowired
    private final AdminQueryFacade queryFacade;
    @Autowired
    private final ControllerUtils utils;

    public DefaultAdminController(AdminCommandFacade commandFacade, AdminQueryFacade queryFacade, ControllerUtils utils) {
        this.commandFacade = commandFacade;
        this.queryFacade = queryFacade;
        this.utils = utils;
    }

    @Override
    public ResponseEntity<Response<AdminDTO>> create(RequestAdminLoginDTO createAdminDTO) {
        Response<AdminDTO> response = new Response<>();
        ResError error = new ResError();
        AdminDTO adminDTO = commandFacade.create(createAdminDTO);
        response.setData(adminDTO);

        if (Objects.isNull(adminDTO)) {
            return utils.getBadResponseEntity(response, error, HttpStatus.NOT_FOUND, "Admin not created");
        } else {
            return utils.getResponseEntityOk(response, error);
        }
    }

    @Override
    public ResponseEntity<Response<AdminDTO>> login(RequestAdminLoginDTO adminLoginDTO) {
        Response<AdminDTO> response = new Response<>();
        ResError error = new ResError();
        AdminDTO adminDTO = commandFacade.login(adminLoginDTO);
        response.setData(adminDTO);

        if (Objects.isNull(adminDTO)) {
            return utils.getBadResponseEntity(response, error, HttpStatus.NOT_FOUND, "Admin not logged in");
        } else {
            return utils.getResponseEntityOk(response, error);
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
            return utils.getResponseEntityOk(response, error);
        } else {
            return utils.getBadResponseEntity(response, error, HttpStatus.NOT_FOUND, "Admin password not updated");
        }
    }

    @Override
    public ResponseEntity<Response<Boolean>> logout(UUID id) {
        Response<Boolean> response = new Response<>();
        ResError error = new ResError();
        Boolean isLogout = commandFacade.logout(id);
        response.setData(isLogout);

        if (isLogout) {
            return utils.getResponseEntityOk(response, error);
        } else {
            return utils.getBadResponseEntity(response, error, HttpStatus.NOT_FOUND, "Admin not exists");
        }
    }
}

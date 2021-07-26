package com.zeroim.admin.facades.command.admin;

import com.zeroim.admin.requests.admin.AdminDTO;
import com.zeroim.admin.requests.admin.RequestAdminLoginDTO;
import com.zeroim.admin.requests.admin.RequestUpdatePasswordDTO;
import com.zeroim.admin.util.BussinessExceptions.InvalidPasswordException;

import java.util.UUID;

public interface AdminCommandFacade {
    AdminDTO create(RequestAdminLoginDTO adminDTO);

    AdminDTO login(RequestAdminLoginDTO adminLoginDTO);

    Boolean updatePassword(RequestUpdatePasswordDTO updatePasswordDTO) throws InvalidPasswordException;

    Boolean logout(UUID id);
}

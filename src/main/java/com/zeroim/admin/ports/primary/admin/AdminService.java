package com.zeroim.admin.ports.primary.admin;

import com.zeroim.admin.domain.admin.Admin;
import com.zeroim.admin.requests.admin.RequestUpdatePasswordDTO;
import com.zeroim.admin.util.BussinessExceptions.InvalidPasswordException;

import java.util.UUID;

public interface AdminService {
    Admin create(Admin admin);

    Admin login(Admin admin);

    Boolean updatePassword(Admin admin);

    void validateOldPassword(RequestUpdatePasswordDTO updatePasswordDTO) throws InvalidPasswordException;

    Admin findByUsername(String username);

    Boolean logout(UUID id);
}

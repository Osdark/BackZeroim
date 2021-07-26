package com.zeroim.admin.populators.admin;

import com.zeroim.admin.domain.admin.Admin;
import com.zeroim.admin.populators.Populator;
import com.zeroim.admin.requests.admin.RequestAdminLoginDTO;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.UUID;

public class LoginAdminPopulator implements Populator<RequestAdminLoginDTO, Admin> {

    @Override
    public void populate(RequestAdminLoginDTO adminLoginDTO, Admin admin) {
        admin.setId(UUID.randomUUID());
        String hashedPassword = BCrypt.hashpw(adminLoginDTO.getPassword(), BCrypt.gensalt(10));
        admin.setPassword(hashedPassword);
        admin.setUsername(adminLoginDTO.getUsername());
        admin.setEmail(adminLoginDTO.getEmail());
    }
}

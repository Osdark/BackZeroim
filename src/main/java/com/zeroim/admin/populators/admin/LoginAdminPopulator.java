package com.zeroim.admin.populators.admin;

import com.zeroim.admin.domain.admin.Admin;
import com.zeroim.admin.populators.Populator;
import com.zeroim.admin.requests.admin.RequestAdminLoginDTO;

public class LoginAdminPopulator implements Populator<RequestAdminLoginDTO, Admin> {

    @Override
    public void populate(RequestAdminLoginDTO adminLoginDTO, Admin admin) {
        admin.setPassword(adminLoginDTO.getPassword());
        admin.setUsername(adminLoginDTO.getUsername());
    }
}

package com.zeroim.admin.populators.admin;

import com.zeroim.admin.domain.admin.Admin;
import com.zeroim.admin.populators.Populator;
import com.zeroim.admin.requests.admin.AdminDTO;

public class AdminResponsePopulator implements Populator<Admin, AdminDTO> {
    @Override
    public void populate(Admin admin, AdminDTO adminDTO) {
        adminDTO.setId(admin.getId());
        adminDTO.setUsername(admin.getUsername());
        adminDTO.setLastLogin(admin.getLastLogin());
        adminDTO.setToken(admin.getToken());
    }
}

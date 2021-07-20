package com.zeroim.admin.populators.admin;

import com.zeroim.admin.domain.admin.Admin;
import com.zeroim.admin.populators.Populator;
import com.zeroim.admin.requests.admin.AdminDTO;

import java.util.UUID;

public class AdminRequestPopulator implements Populator<AdminDTO, Admin> {
    @Override
    public void populate(AdminDTO adminDTO, Admin admin) {
        admin.setId(UUID.randomUUID());
        admin.setUsername(adminDTO.getUsername());
        admin.setLastLogin(adminDTO.getLastLogin());
    }
}

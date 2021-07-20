package com.zeroim.admin.populators.admin;

import com.zeroim.admin.domain.admin.Admin;
import com.zeroim.admin.populators.Populator;
import com.zeroim.admin.requests.admin.RequestUpdatePasswordDTO;

public class UpdatePasswordDTOAdminPopulator implements Populator<RequestUpdatePasswordDTO, Admin> {
    @Override
    public void populate(RequestUpdatePasswordDTO updatePasswordDTO, Admin admin) {
        admin.setPassword(updatePasswordDTO.getNewPassword());
        admin.setId(updatePasswordDTO.getAdminId());
    }
}

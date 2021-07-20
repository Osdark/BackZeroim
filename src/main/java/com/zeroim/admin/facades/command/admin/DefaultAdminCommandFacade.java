package com.zeroim.admin.facades.command.admin;

import com.zeroim.admin.converter.Converter;
import com.zeroim.admin.domain.admin.Admin;
import com.zeroim.admin.populators.Populator;
import com.zeroim.admin.populators.admin.AdminRequestPopulator;
import com.zeroim.admin.populators.admin.AdminResponsePopulator;
import com.zeroim.admin.populators.admin.LoginAdminPopulator;
import com.zeroim.admin.populators.admin.UpdatePasswordDTOAdminPopulator;
import com.zeroim.admin.ports.primary.admin.AdminService;
import com.zeroim.admin.requests.admin.AdminDTO;
import com.zeroim.admin.requests.admin.RequestAdminLoginDTO;
import com.zeroim.admin.requests.admin.RequestUpdatePasswordDTO;
import com.zeroim.admin.util.BussinessExceptions.InvalidPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultAdminCommandFacade implements AdminCommandFacade {
    @Autowired
    private final AdminService service;

    public DefaultAdminCommandFacade(AdminService service) {
        this.service = service;
    }

    @Override
    public AdminDTO create(AdminDTO adminDTO) {
        return convertAdmin(service.create(convertAdminDTO(adminDTO)));
    }

    @Override
    public AdminDTO login(RequestAdminLoginDTO adminLoginDTO) {
        return convertAdmin(service.login(convertAdminLoginDTO(adminLoginDTO)));
    }

    @Override
    public Boolean updatePassword(RequestUpdatePasswordDTO updatePasswordDTO) throws InvalidPasswordException {
        service.validateOldPassword(updatePasswordDTO);
        return service.updatePassword(convertUpdatePassword(updatePasswordDTO));
    }

    private Admin convertUpdatePassword(RequestUpdatePasswordDTO updatePasswordDTO) {
        Converter<RequestUpdatePasswordDTO, Admin> updatePasswordDTOAdminConverter = buildUpdatePasswordDTOAdminConverter();
        return updatePasswordDTOAdminConverter.convert(updatePasswordDTO);
    }

    private Converter<RequestUpdatePasswordDTO, Admin> buildUpdatePasswordDTOAdminConverter() {
        Populator<RequestUpdatePasswordDTO, Admin> updatePasswordDTOAdminPopulator = new UpdatePasswordDTOAdminPopulator();
        return Converter.of(Admin.class).withPopulator(updatePasswordDTOAdminPopulator);
    }

    private Admin convertAdminLoginDTO(RequestAdminLoginDTO adminLoginDTO) {
        Converter<RequestAdminLoginDTO, Admin> loginAdminConverter = buildLoginAdminConverter();
        return loginAdminConverter.convert(adminLoginDTO);
    }

    private Converter<RequestAdminLoginDTO, Admin> buildLoginAdminConverter() {
        Populator<RequestAdminLoginDTO, Admin> loginAdminPopulator = new LoginAdminPopulator();
        return Converter.of(Admin.class).withPopulator(loginAdminPopulator);
    }

    private Admin convertAdminDTO(AdminDTO adminDTO) {
        Converter<AdminDTO, Admin> requestConverter = buildRequestConverter();
        return requestConverter.convert(adminDTO);
    }

    private Converter<AdminDTO, Admin> buildRequestConverter() {
        Populator<AdminDTO, Admin> requestPopulator = new AdminRequestPopulator();
        return Converter.of(Admin.class).withPopulator(requestPopulator);
    }

    private AdminDTO convertAdmin(Admin admin) {
        Converter<Admin, AdminDTO> responseConverter = buildResponseConverter();
        return responseConverter.convert(admin);
    }

    private Converter<Admin, AdminDTO> buildResponseConverter() {
        Populator<Admin, AdminDTO> responsePopulator = new AdminResponsePopulator();
        return Converter.of(AdminDTO.class).withPopulator(responsePopulator);
    }
}

package com.zeroim.admin.facades.command.admin;

import com.zeroim.admin.adapters.admin.AdminLoginService;
import com.zeroim.admin.converter.Converter;
import com.zeroim.admin.domain.admin.Admin;
import com.zeroim.admin.populators.Populator;
import com.zeroim.admin.populators.admin.*;
import com.zeroim.admin.ports.primary.admin.AdminService;
import com.zeroim.admin.requests.admin.AdminDTO;
import com.zeroim.admin.requests.admin.RequestAdminLoginDTO;
import com.zeroim.admin.requests.admin.RequestUpdatePasswordDTO;
import com.zeroim.admin.util.BussinessExceptions.InvalidPasswordException;
import com.zeroim.admin.util.Constants;
import com.zeroim.admin.util.JwtUtil;
import io.jsonwebtoken.lang.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DefaultAdminCommandFacade implements AdminCommandFacade {
    @Autowired
    private final AdminService service;
    @Autowired
    private final JwtUtil jwtUtil;
    @Autowired
    private final AdminLoginService adminLoginService;

    @Value("${admin.session.expiration-time}")
    private int EXPIRATION_TIME;

    public DefaultAdminCommandFacade(AdminService service, JwtUtil jwtUtil, AdminLoginService adminLoginService) {
        this.service = service;
        this.jwtUtil = jwtUtil;
        this.adminLoginService = adminLoginService;
    }

    @Override
    public AdminDTO create(RequestAdminLoginDTO adminDTO) {
        return convertAdmin(service.create(convertAdminLoginDTO(adminDTO)));
    }

    @Override
    public AdminDTO login(RequestAdminLoginDTO adminLoginDTO) {
        Admin admin = service.findByUsername(adminLoginDTO.getUsername());
        boolean validPassword = BCrypt.checkpw(adminLoginDTO.getPassword(), admin.getPassword());
        if (!admin.getToken().isEmpty()) {
            return null;
        }
        generateAdminSessionToken(admin);
        return validPassword ? convertAdmin(service.login(admin)) : null;
    }

    @Override
    public Boolean updatePassword(RequestUpdatePasswordDTO updatePasswordDTO) throws InvalidPasswordException {
        service.validateOldPassword(updatePasswordDTO);
        return service.updatePassword(convertUpdatePassword(updatePasswordDTO));
    }

    @Override
    public Boolean logout(UUID id) {
        return service.logout(id);
    }

    private void generateAdminSessionToken(Admin admin) {
        UserDetails userDetails = adminLoginService.loadUserByUsername(admin.getUsername());
        String token = jwtUtil.generateToken(userDetails, EXPIRATION_TIME, Constants.SESSION.name());
        admin.setToken(token);
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

    private AdminDTO convertAdmin(Admin admin) {
        Converter<Admin, AdminDTO> responseConverter = buildResponseConverter();
        return responseConverter.convert(admin);
    }

    private Converter<Admin, AdminDTO> buildResponseConverter() {
        Populator<Admin, AdminDTO> responsePopulator = new AdminResponsePopulator();
        return Converter.of(AdminDTO.class).withPopulator(responsePopulator);
    }
}

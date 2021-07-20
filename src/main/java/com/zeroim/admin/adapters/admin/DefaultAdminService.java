package com.zeroim.admin.adapters.admin;

import com.zeroim.admin.domain.admin.Admin;
import com.zeroim.admin.ports.primary.admin.AdminService;
import com.zeroim.admin.ports.secondary.admin.AdminRepo;
import com.zeroim.admin.requests.admin.RequestUpdatePasswordDTO;
import com.zeroim.admin.util.BussinessExceptions.InvalidPasswordException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class DefaultAdminService implements AdminService {
    private final AdminRepo repo;

    public DefaultAdminService(AdminRepo repo) {
        this.repo = repo;
    }

    @Override
    public Admin create(Admin admin) {
        return repo.save(admin);
    }

    @Override
    public Admin login(Admin admin) {
        Optional<Admin> adminDB = repo.findById(admin.getId());
        if (adminDB.isPresent()) {
            admin.setLastLogin(new Date());
            return repo.save(admin);
        }
        return null;
    }

    @Override
    public Boolean updatePassword(Admin admin) {
        Optional<Admin> adminDB = repo.findById(admin.getId());
        if (adminDB.isPresent()) {
            adminDB.get().setPassword(admin.getPassword());
            repo.save(adminDB.get());
            return true;
        }
        return false;
    }

    @Override
    public void validateOldPassword(RequestUpdatePasswordDTO updatePasswordDTO)
            throws InvalidPasswordException {
        Optional<Admin> admin = repo.findByIdAndPassword(updatePasswordDTO.getAdminId(),
                updatePasswordDTO.getOldPassword());
        if (!admin.isPresent()) throw new InvalidPasswordException("Invalid credentials for admin");
    }

    @Override
    public Admin findByUsername(String username) {
        return repo.findByUsername(username);
    }
}

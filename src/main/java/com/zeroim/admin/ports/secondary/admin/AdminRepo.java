package com.zeroim.admin.ports.secondary.admin;

import com.zeroim.admin.domain.admin.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdminRepo extends CrudRepository<Admin, UUID> {
    Optional<Admin> findByIdAndPassword(UUID adminId, String oldPassword);

    Admin findByUsername(String username);

    Optional<Admin> existsByUsername(String username);
}

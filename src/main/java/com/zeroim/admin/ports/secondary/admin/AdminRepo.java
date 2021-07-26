package com.zeroim.admin.ports.secondary.admin;

import com.zeroim.admin.domain.admin.Admin;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdminRepo extends CrudRepository<Admin, UUID> {
    Optional<Admin> findByIdAndPassword(UUID adminId, String oldPassword);

    @Query("{ 'username' : ?0 }")
    Admin findByUsername(String username);

    @Query("{ 'username' : ?0 }")
    Optional<Admin> existsByUsername(String username);

    @Query("{ 'username' : ?0 , 'password': ?1 }")
    Optional<Admin> existsByUsernameAndPassword(String username, String password);
}

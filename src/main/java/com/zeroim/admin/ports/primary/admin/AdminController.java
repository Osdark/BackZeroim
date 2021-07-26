package com.zeroim.admin.ports.primary.admin;

import com.zeroim.admin.requests.admin.AdminDTO;
import com.zeroim.admin.requests.admin.RequestAdminLoginDTO;
import com.zeroim.admin.requests.admin.RequestUpdatePasswordDTO;
import com.zeroim.admin.requests.util.Response;
import com.zeroim.admin.util.BussinessExceptions.AdminLoggedException;
import com.zeroim.admin.util.BussinessExceptions.InvalidPasswordException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/admin/security")
public interface AdminController {
    @PostMapping
    ResponseEntity<Response<AdminDTO>> create(@RequestBody RequestAdminLoginDTO adminDTO);

    @PostMapping("/login")
    ResponseEntity<Response<AdminDTO>> login(@RequestBody RequestAdminLoginDTO adminLoginDTO) throws AdminLoggedException, InvalidPasswordException;

    @PutMapping("/updatePassword")
    ResponseEntity<Response<Boolean>> updatePassword(@RequestBody RequestUpdatePasswordDTO updatePasswordDTO) throws InvalidPasswordException;

    @PostMapping("/logout")
    ResponseEntity<Response<Boolean>> logout(@RequestParam("id") UUID id);
}

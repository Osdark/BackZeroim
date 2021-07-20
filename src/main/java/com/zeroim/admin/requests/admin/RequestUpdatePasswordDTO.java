package com.zeroim.admin.requests.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestUpdatePasswordDTO {
    private String oldPassword;
    private String newPassword;
    private UUID adminId;
}

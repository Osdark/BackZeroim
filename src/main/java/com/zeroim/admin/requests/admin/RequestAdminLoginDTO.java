package com.zeroim.admin.requests.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestAdminLoginDTO {
    private String username;
    private String password;
    private String email;
}

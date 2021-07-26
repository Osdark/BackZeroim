package com.zeroim.admin.requests.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDTO {
    private UUID id;
    private String username;
    private Date lastLogin;
    private String token;
}

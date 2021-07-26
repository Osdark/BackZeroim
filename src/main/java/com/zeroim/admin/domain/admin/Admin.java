package com.zeroim.admin.domain.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.Nullable;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Admin")
public class Admin {
    @Id
    @Nullable
    private UUID id;
    private String username;
    private String password;
    private String email;
    private Date lastLogin;
    private boolean logged;
    private String token;
}

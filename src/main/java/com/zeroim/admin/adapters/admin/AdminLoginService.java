package com.zeroim.admin.adapters.admin;

import com.zeroim.admin.domain.admin.Admin;
import com.zeroim.admin.ports.primary.admin.AdminService;
import com.zeroim.admin.util.Constants;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AdminLoginService implements UserDetailsService {
    private final AdminService service;

    public AdminLoginService(AdminService service) {
        this.service = service;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = service.findByUsername(username);
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(Constants.ADMIN.name()));
        return new User(admin.getUsername(), admin.getPassword(), roles);
    }
}

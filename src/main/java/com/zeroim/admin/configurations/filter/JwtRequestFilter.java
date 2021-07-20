package com.zeroim.admin.configurations.filter;

import com.zeroim.admin.adapters.admin.AdminLoginService;
import com.zeroim.admin.domain.admin.Admin;
import com.zeroim.admin.ports.secondary.admin.AdminRepo;
import com.zeroim.admin.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    private final AdminRepo repo;
    @Autowired
    private AdminLoginService adminLoginService;
    @Autowired
    private JwtUtil jwtUtil;


    public JwtRequestFilter(AdminRepo repo) {
        this.repo = repo;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse, FilterChain filterChain)
            throws ServletException, IOException {
        final String authorizationHeader = httpServletRequest.getHeader("Authorization");

        String userName = null;
        String jwt = null;
        String issuer = null;
        String ip = httpServletRequest.getRemoteAddr();

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            userName = jwtUtil.extractUserName(jwt);
            issuer = jwtUtil.extractIssuer(jwt);
        }

        checkValidUsername(httpServletRequest, userName, jwt, issuer, ip);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void checkValidUsername(HttpServletRequest httpServletRequest, String userName, String jwt, String issuer, String ip) {
        if (isValidUsername(userName) && isActiveSession(userName)) {
            UserDetails userDetails = adminLoginService.loadUserByUsername(userName);
            checkValidToken(httpServletRequest, jwt, userDetails);
        }
    }

    private boolean isValidUsername(String userName) {
        return userName != null && SecurityContextHolder.getContext().getAuthentication() == null;
    }

    private void checkValidToken(HttpServletRequest httpServletRequest, String jwt, UserDetails userDetails) {
        if (jwtUtil.validateToken(jwt, userDetails)) {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
    }

    private boolean isActiveSession(String username) {
        boolean validSession;
        Optional<Admin> admin = repo.existsByUsername(username);
        validSession = admin.isPresent();
        return validSession;
    }

    private String getSessionIp(String sessionId) {
        return new String(Base64.getDecoder().decode(Base64.getDecoder()
                .decode(sessionId.getBytes()))).split(",")[2];
    }
}

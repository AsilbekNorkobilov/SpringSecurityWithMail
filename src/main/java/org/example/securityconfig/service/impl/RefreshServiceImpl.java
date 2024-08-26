package org.example.securityconfig.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.securityconfig.security.CustomUserDetailsService;
import org.example.securityconfig.security.JwtUtil;
import org.example.securityconfig.service.RefreshService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshServiceImpl implements RefreshService {
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;
    @Override
    public String refresh() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
        return "Bearer "+jwtUtil.generateToken(userDetails);
    }
}

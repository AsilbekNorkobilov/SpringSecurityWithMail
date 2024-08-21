package org.example.securityconfig.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.securityconfig.dto.LoginDto;
import org.example.securityconfig.security.CustomUserDetailsService;
import org.example.securityconfig.security.JwtUtil;
import org.example.securityconfig.service.AuthService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    @Override
    public String login(LoginDto loginDto) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginDto.getEmail());
        if (passwordEncoder.matches(loginDto.getPassword(),userDetails.getPassword())){
            return jwtUtil.generateToken(loginDto);
        }
        return "xatolik";
    }
}

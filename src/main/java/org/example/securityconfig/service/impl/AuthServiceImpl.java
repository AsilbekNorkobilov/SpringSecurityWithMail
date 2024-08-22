package org.example.securityconfig.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.securityconfig.dto.LoginDto;
import org.example.securityconfig.security.JwtUtil;
import org.example.securityconfig.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    @Override
    public String login(LoginDto loginDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPassword()));
        return jwtUtil.generateToken(loginDto);
    }
}

package org.example.securityconfig.service;

import org.example.securityconfig.dto.LoginDto;

public interface AuthService {
    String login(LoginDto loginDto);
}

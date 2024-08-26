package org.example.securityconfig.service;

import jakarta.servlet.http.HttpServletRequest;
import org.example.securityconfig.dto.ReqDto;
import org.example.securityconfig.dto.TokenDto;

public interface AuthService {
    TokenDto login(ReqDto loginDto);

    String register(ReqDto reqDto);

    TokenDto confirmMailCodeAndRegister(Integer code, HttpServletRequest request);
}

package org.example.securityconfig.service;

import jakarta.servlet.http.HttpServletRequest;
import org.example.securityconfig.dto.ReqDto;

public interface AuthService {
    String login(ReqDto loginDto);

    String register(ReqDto reqDto);

    String confirmMailCodeAndRegister(Integer code, HttpServletRequest request);
}

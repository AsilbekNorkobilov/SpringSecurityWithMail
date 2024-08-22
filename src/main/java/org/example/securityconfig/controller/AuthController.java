package org.example.securityconfig.controller;

import lombok.RequiredArgsConstructor;
import org.example.securityconfig.dto.LoginDto;
import org.example.securityconfig.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("api/auth")
@RestController
public class AuthController {
    private final AuthService authService;

    @PostMapping("login")
    public String login(@RequestBody LoginDto loginDto){
        return authService.login(loginDto);
    }

    @PostMapping("register")
    public String register() {
        return "registered";
    }
}



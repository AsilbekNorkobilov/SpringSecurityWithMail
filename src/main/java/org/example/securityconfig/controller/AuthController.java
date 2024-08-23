package org.example.securityconfig.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.securityconfig.dto.CodeRequestDto;
import org.example.securityconfig.dto.ReqDto;
import org.example.securityconfig.dto.ResponseDto;
import org.example.securityconfig.service.AuthService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("api/auth")
@RestController
public class AuthController {
    @PostMapping("login")
    public HttpEntity<?> login(@RequestBody ReqDto reqDto){
        String token = authService.login(reqDto);
        return ResponseEntity.ok(ResponseDto.builder().message("Authorization token").body(token).build());
    }

    private final AuthService authService;

    @PostMapping("register")
    public HttpEntity<?> register(@RequestBody ReqDto reqDto) {
        String confirmToken = authService.register(reqDto);
        return ResponseEntity.ok(ResponseDto.builder().message("Confirm Token").body(confirmToken).build());
    }

    @PostMapping("confirm")
    public HttpEntity<?> confirmMailCode(@RequestBody CodeRequestDto codeDto, HttpServletRequest request){
        String token = authService.confirmMailCodeAndRegister(codeDto.getCode(), request);
        return ResponseEntity.ok(ResponseDto.builder().message("Authorization token").body(token).build());
    }
}



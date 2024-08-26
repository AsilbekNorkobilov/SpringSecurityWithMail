package org.example.securityconfig.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.securityconfig.dto.CodeRequestDto;
import org.example.securityconfig.dto.ReqDto;
import org.example.securityconfig.dto.ResponseDto;
import org.example.securityconfig.dto.TokenDto;
import org.example.securityconfig.service.AuthService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("api/auth")
@RestController
public class AuthController {
    private final AuthService authService;

    @PostMapping("login")
    public HttpEntity<?> login(@RequestBody ReqDto reqDto){
        TokenDto token = authService.login(reqDto);
        return ResponseEntity.ok(ResponseDto.builder().message("Authorization token").body(token).build());
    }


    @PostMapping("register")
    public HttpEntity<?> register(@RequestBody ReqDto reqDto) {
        String confirmToken = authService.register(reqDto);
        return ResponseEntity.ok("Confirm "+confirmToken);
    }

    @PostMapping("confirm")
    public HttpEntity<?> confirmMailCode(@RequestBody CodeRequestDto codeDto, HttpServletRequest request){
        TokenDto token = authService.confirmMailCodeAndRegister(codeDto.getCode(), request);
        return ResponseEntity.ok(ResponseDto.builder().message("Authorization token").body(token).build());
    }
}



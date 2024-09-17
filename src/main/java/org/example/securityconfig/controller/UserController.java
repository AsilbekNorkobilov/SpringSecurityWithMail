package org.example.securityconfig.controller;

import lombok.RequiredArgsConstructor;
import org.example.securityconfig.component.CurrentUser;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
public class UserController {
    private final CurrentUser currentUser;
    @GetMapping("me")
    public HttpEntity<?> getMe(){
        String me = currentUser.getMe();
        System.out.println(me);
        return ResponseEntity.ok(me);
    }
}

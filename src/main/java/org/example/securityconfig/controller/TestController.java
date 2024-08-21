package org.example.securityconfig.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/test")
public class TestController {

    @GetMapping("all")
    public String test(){
        return "blablabla";
    }

    @GetMapping("user")
    private String forUser(){
        return "user krdi";
    }
    @GetMapping("admin")
    private String forAdmin(){
        return "admin krdi";
    }


}

package org.example.securityconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SecurityConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityConfigApplication.class, args);
    }

}

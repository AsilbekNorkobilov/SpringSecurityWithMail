package org.example.securityconfig.component;

import lombok.RequiredArgsConstructor;
import org.example.securityconfig.entity.Role;
import org.example.securityconfig.entity.User;
import org.example.securityconfig.entity.enums.RoleName;
import org.example.securityconfig.repo.RoleRepository;
import org.example.securityconfig.repo.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddl;
    @Override
    public void run(String... args) throws Exception {
        if (ddl.equals("create")){
            Role roleAdmin=new Role(1, RoleName.ROLE_ADMIN);
            Role roleUser=new Role(2,RoleName.ROLE_USER);
            roleRepository.save(roleAdmin);
            roleRepository.save(roleUser);
            User admin=User.builder()
                    .email("a@gmail.com")
                    .password(passwordEncoder.encode("root123"))
                    .roles(List.of(roleAdmin))
                    .build();
            userRepository.save(admin);
        }
    }
}

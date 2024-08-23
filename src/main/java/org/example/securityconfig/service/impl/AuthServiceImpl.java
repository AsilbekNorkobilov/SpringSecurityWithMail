package org.example.securityconfig.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.securityconfig.component.MailCodeSender;
import org.example.securityconfig.dto.ReqDto;
import org.example.securityconfig.entity.Role;
import org.example.securityconfig.entity.User;
import org.example.securityconfig.entity.enums.RoleName;
import org.example.securityconfig.repo.RoleRepository;
import org.example.securityconfig.repo.UserRepository;
import org.example.securityconfig.security.JwtUtil;
import org.example.securityconfig.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final MailCodeSender mailCodeSender;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public String login(ReqDto loginDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPassword()));
        return jwtUtil.generateToken(loginDto);
    }

    @Override
    public String register(ReqDto reqDto) {
        Integer code=new Random().nextInt(1000,10000);
        mailCodeSender.sendMessage(code,reqDto.getEmail());
        return jwtUtil.generateConfirmToken(reqDto,code);
    }

    @Override
    public String confirmMailCodeAndRegister(Integer code, HttpServletRequest request) {
        String confirmToken = request.getHeader("Confirm");
        Integer mailCode = jwtUtil.getMailCode(confirmToken);
        ReqDto reqDto = jwtUtil.getReqDto(confirmToken);
        if (code.equals(mailCode)){
            Role userRole = roleRepository.findRoleByRoleName(RoleName.ROLE_USER);
            User user=User.builder()
                    .roles(List.of(userRole))
                    .email(reqDto.getEmail())
                    .password(passwordEncoder.encode(reqDto.getPassword()))
                    .build();
            userRepository.save(user);
            return jwtUtil.generateToken(reqDto);
        }
        throw new BadCredentialsException("Invalid code try again");
    }
}

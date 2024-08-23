package org.example.securityconfig.component;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailCodeSender {
    private final JavaMailSender javaMailSender;

    @Async
    public void sendMessage(Integer code, String email){
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setSubject("Auth Code");
        mailMessage.setText("Your verification code is :"+code);
        mailMessage.setTo(email);
        javaMailSender.send(mailMessage);
    }
}

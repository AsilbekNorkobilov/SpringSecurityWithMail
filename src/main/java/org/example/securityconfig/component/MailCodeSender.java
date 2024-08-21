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
    public void sendMessage(){
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setSubject("Auth Code");
        mailMessage.setText("1234");
        mailMessage.setTo("4steeve82@gmail.com");
        javaMailSender.send(mailMessage);
    }
}

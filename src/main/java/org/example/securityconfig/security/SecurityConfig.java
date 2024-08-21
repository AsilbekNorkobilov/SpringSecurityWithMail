package org.example.securityconfig.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    
    @Bean
    @SuppressWarnings("removal")
    public SecurityFilterChain securityFilterChain(HttpSecurity http, CustomAuthenticationEntryPoint e) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(m->{
            m.requestMatchers("/api/auth/**").permitAll();
            m.requestMatchers("/api/test/all").permitAll();
            m.requestMatchers("api/test/user").hasRole("USER");
            m.requestMatchers("api/test/admin").hasRole("ADMIN");
            m.anyRequest().authenticated();
        });
        http.sessionManagement(m->m.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.exceptionHandling()
                .authenticationEntryPoint(e);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

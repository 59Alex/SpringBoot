package com.preproject.thirdmodule.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Configuration
public class EncoderConfig {
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        System.out.println(new BCryptPasswordEncoder().encode("123"));
        return new BCryptPasswordEncoder();
    }
}

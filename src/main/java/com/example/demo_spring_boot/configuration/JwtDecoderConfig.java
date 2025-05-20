package com.example.demo_spring_boot.configuration;

import com.example.demo_spring_boot.repository.InvalidatedTokenRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtDecoderConfig {

    private final InvalidatedTokenRepository invalidatedTokenRepository;

    public JwtDecoderConfig(InvalidatedTokenRepository invalidatedTokenRepository) {
        this.invalidatedTokenRepository = invalidatedTokenRepository;
    }

    @Bean
    public CustomJwtDecoder customJwtDecoder() {
        return new CustomJwtDecoder(invalidatedTokenRepository);
    }
}


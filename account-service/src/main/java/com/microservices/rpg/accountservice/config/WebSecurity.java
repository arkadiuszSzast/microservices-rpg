package com.microservices.rpg.accountservice.config;

import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebSecurity {
    @Bean
    public PrincipalExtractor googlePrincipalExtractor() {
        return new OktaPrincipalExtractor();
    }
}

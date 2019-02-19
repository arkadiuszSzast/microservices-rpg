package com.microservices.rpg.accountservice.config;

import com.microservices.rpg.accountservice.account.AccountCreateService;
import com.microservices.rpg.accountservice.account.domain.AccountRepository;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebSecurity {

    private final AccountRepository accountRepository;
    private final AccountCreateService accountCreateService;

    public WebSecurity(AccountRepository accountRepository,
                       AccountCreateService accountCreateService) {
        this.accountRepository = accountRepository;
        this.accountCreateService = accountCreateService;
    }

    @Bean
    public PrincipalExtractor oktaPrincipalExtractor() {
        return new OktaPrincipalExtractor(accountRepository, accountCreateService);
    }
}

package com.microservices.rpg.accountservice.config;

import com.microservices.rpg.accountservice.account.AccountCreateService;
import com.microservices.rpg.accountservice.account.domain.AccountRepository;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;

import java.util.Map;

public class OktaPrincipalExtractor implements PrincipalExtractor {

    private final AccountRepository accountRepository;
    private final AccountCreateService accountCreateService;

    public OktaPrincipalExtractor(AccountRepository accountRepository,
                                  AccountCreateService accountCreateService) {
        this.accountRepository = accountRepository;
        this.accountCreateService = accountCreateService;
    }

    @Override
    public Object extractPrincipal(Map<String, Object> map) {
        var accountId = (String) map.get("sub");
        var account = accountRepository.findById(accountId);
        if (account.isEmpty()) {
            return accountCreateService.create(map);
        }
        return account.get();
    }
}

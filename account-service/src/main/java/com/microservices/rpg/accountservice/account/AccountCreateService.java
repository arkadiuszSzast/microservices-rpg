package com.microservices.rpg.accountservice.account;

import com.microservices.rpg.accountservice.account.domain.Account;
import com.microservices.rpg.accountservice.account.domain.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.logging.Logger;

@Service
public class AccountCreateService {

    private Logger logger = Logger.getLogger(AccountCreateService.class.getName());

    private final AccountRepository accountRepository;

    public AccountCreateService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account create(Map<String, Object> map) {
        var account = new Account((String) map.get("sub"), (String) map.get("name"), (String) map.get("email"));
        logger.info(String.format("Creating new account instance with id: %s in database", account.getId()));
        return accountRepository.save(account);
    }
}

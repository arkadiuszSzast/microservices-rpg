package com.microservices.rpg.accountservice.account;

import com.microservices.rpg.accountservice.account.domain.Account;
import com.microservices.rpg.accountservice.account.domain.AccountRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AccountUpdateLastLogInService {

    private final AccountRepository accountRepository;

    public AccountUpdateLastLogInService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void update(Account account) {
        account.setLastLogInDate(LocalDateTime.now());
        accountRepository.save(account);
    }
}

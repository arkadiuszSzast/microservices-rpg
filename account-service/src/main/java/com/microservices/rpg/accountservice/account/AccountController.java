package com.microservices.rpg.accountservice.account;


import com.microservices.rpg.accountservice.account.domain.Account;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class AccountController {

    private Logger logger = Logger.getLogger(AccountController.class.getName());

    private final AccountUpdateLastLogInService accountUpdateLastLogInService;

    public AccountController(AccountUpdateLastLogInService accountUpdateLastLogInService) {
        this.accountUpdateLastLogInService = accountUpdateLastLogInService;
    }

    @GetMapping("/account")
    public Account getAccount(@AuthenticationPrincipal Account account) {
        logger.info(String.format("Getting info about user: %s", account.getId()));
        return account;
    }

    @PutMapping("/account/last-login")
    public void setLastLogIn(@AuthenticationPrincipal Account account) {
        logger.info(String.format("Updating last login date for user: %s", account.getId()));
        accountUpdateLastLogInService.update(account);
    }
}

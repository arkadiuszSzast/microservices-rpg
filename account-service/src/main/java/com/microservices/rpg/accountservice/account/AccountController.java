package com.microservices.rpg.accountservice.account;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class AccountController {

    private Logger logger = Logger.getLogger(AccountController.class.getName());

    @GetMapping("/account")
    public Account getAccount(@AuthenticationPrincipal Account account) {
        logger.info(String.format("Getting info about user: %s", account.getId()));
        return account;
    }
}

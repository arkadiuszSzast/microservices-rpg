package com.microservices.rpg.accountservice.account;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @GetMapping("/account")
    public Account getAccount(@AuthenticationPrincipal Account account) {
        return account;
    }
}

package com.microservices.rpg.accountservice.account;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class AccountController {

    @GetMapping("/account")
    public OAuth2Authentication getAccount(Principal principal) {
        OAuth2Authentication authentication = (OAuth2Authentication) principal;
        return authentication;
    }
}

package com.microservices.rpg.accountservice.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@RestController
public class AccountController {

    @Qualifier("oauth2ClientContext")
    @Autowired
    private OAuth2ClientContext clientContext;

    @GetMapping("/account")
    public Account getAccount(Principal principal) {
        OAuth2Authentication authentication = (OAuth2Authentication) principal;
        Map<String, String> details = (Map<String, String>) authentication.getUserAuthentication().getDetails();
        return new Account(details.get("sub"), details.get("name"), details.get("email"));
    }
}

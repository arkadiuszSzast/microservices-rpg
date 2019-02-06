package com.microservices.rpg.accountservice.config;

import com.microservices.rpg.accountservice.account.Account;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;

import java.util.Map;

public class OktaPrincipalExtractor implements PrincipalExtractor {
    @Override
    public Object extractPrincipal(Map<String, Object> map) {
        return new Account((String) map.get("sub"), (String) map.get("name"), (String) map.get("email"));
    }
}

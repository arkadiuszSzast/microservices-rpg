package com.microservices.rpg.zuul.account;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient("account-service")
public interface AccountClient {

    @PutMapping("/account/last-login")
    void setLastLogin();
}
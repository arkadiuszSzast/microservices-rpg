package com.microservices.rpg.characterservice.account;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("account-service")
public interface AccountClient {

    @GetMapping("/account")
    Account getAccount();
}

package com.microservices.rpg.characterservice.character;

import com.microservices.rpg.characterservice.account.Account;
import com.microservices.rpg.characterservice.account.AccountClient;
import com.microservices.rpg.characterservice.config.UserFeignClientInterceptor;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CharacterController {

    private final AccountClient accountClient;

    public CharacterController(AccountClient accountClient) {
        this.accountClient = accountClient;
    }

    @GetMapping("/character")
    @HystrixCommand(fallbackMethod = "fallback")
    @CrossOrigin(origins = "*")
    public String getCharacter() {
        accountClient.getAuth();
        return "CHARACTER";
    }

    @GetMapping("/test")
    public String test() {
        return "TEST";
    }

    @Bean
    public RequestInterceptor getUserFeignClientInterceptor() {
        return new UserFeignClientInterceptor();
    }
}

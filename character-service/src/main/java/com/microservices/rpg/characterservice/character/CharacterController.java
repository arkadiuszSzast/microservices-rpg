package com.microservices.rpg.characterservice.character;

import com.microservices.rpg.characterservice.account.Account;
import com.microservices.rpg.characterservice.account.AccountClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CharacterController {

    private final AccountClient accountClient;

    public CharacterController(AccountClient accountClient) {
        this.accountClient = accountClient;
    }

    @GetMapping("/character")
    public Account getCharacter() {
        return accountClient.getAuth();
    }

    @GetMapping("/test")
    public String test() {
        return "TEST";
    }
}

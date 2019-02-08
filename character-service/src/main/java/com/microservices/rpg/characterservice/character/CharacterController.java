package com.microservices.rpg.characterservice.character;

import com.microservices.rpg.characterservice.account.AccountClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CharacterController {

    private final AccountClient accountClient;
    private final CharacterRepository characterRepository;

    public CharacterController(AccountClient accountClient, CharacterRepository characterRepository) {
        this.accountClient = accountClient;
        this.characterRepository = characterRepository;
    }

    @GetMapping("/character")
    public List<Character> getCharacter() {
        var account = accountClient.getAccount();
        return characterRepository.findByAccount(account);
    }

    @PostMapping("/character")
    public void addCharacter() {
        var account = accountClient.getAccount();
        var character = new Character("Character1", CharacterClass.MAGE, account);
        characterRepository.save(character);
    }
}

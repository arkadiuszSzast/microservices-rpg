package com.microservices.rpg.characterservice.character;

import com.microservices.rpg.characterservice.account.AccountClient;
import com.microservices.rpg.characterservice.character.domain.Character;
import com.microservices.rpg.characterservice.character.domain.CharacterRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public List<Character> getCharacters() {
        var account = accountClient.getAccount();
        return characterRepository.findByAccount(account);
    }

    @PostMapping("/character")
    public void addCharacter(@RequestBody Character character) {
        var account = accountClient.getAccount();
        character.setAccount(account);
        characterRepository.save(character);
    }
}

package com.microservices.rpg.characterservice.character;

import com.microservices.rpg.characterservice.account.AccountClient;
import com.microservices.rpg.characterservice.character.domain.Character;
import com.microservices.rpg.characterservice.character.domain.CharacterRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class CharacterController {

    private Logger logger = Logger.getLogger(CharacterController.class.getName());

    private final AccountClient accountClient;
    private final CharacterRepository characterRepository;

    public CharacterController(AccountClient accountClient, CharacterRepository characterRepository) {
        this.accountClient = accountClient;
        this.characterRepository = characterRepository;
    }

    @GetMapping("/character")
    public List<Character> getCharacters() {
        logger.info("Getting user info using feign client");
        var account = accountClient.getAccount();
        logger.info(String.format("Getting characters of user: %s", account.getId()));
        return characterRepository.findByAccount(account);
    }

    @PostMapping("/character")
    public void addCharacter(@RequestBody Character character) {
        logger.info("Getting user info using feign client");
        var account = accountClient.getAccount();
        character.setAccount(account);
        logger.info(String.format("Adding new character for user: %s", account.getId()));
        characterRepository.save(character);
    }
}

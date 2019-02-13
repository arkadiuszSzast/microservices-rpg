package com.microservices.rpg.characterservice.character;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mongobee.exception.MongobeeException;
import com.microservices.rpg.characterservice.account.Account;
import com.microservices.rpg.characterservice.character.domain.Character;
import com.microservices.rpg.characterservice.character.domain.CharacterClass;
import com.microservices.rpg.characterservice.character.domain.CharacterRepository;
import com.microservices.rpg.characterservice.config.LocalRibbonClientConfiguration;
import com.microservices.rpg.characterservice.db.DbMigration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CharacterControllerTest extends LocalRibbonClientConfiguration {

    private DbMigration dbMigration;
    @Autowired
    private CharacterController characterController;
    @Autowired
    private CharacterRepository characterRepository;

    @BeforeEach
    public void setup() throws MongobeeException {
        dbMigration = new DbMigration();
        dbMigration.execute();
    }

    @Test
    public void shouldReturnCharacters() throws IOException {

        //arrange
        var objectMapper = new ObjectMapper();
        var accountAsBytes = this.getClass().getResourceAsStream("/json/account/account.json").readAllBytes();
        var knightCharacterAsBytes = this.getClass().getResourceAsStream("/json/character/knightCharacter.json").readAllBytes();
        var druidCharacterAsBytes = this.getClass().getResourceAsStream("/json/character/druidCharacter.json").readAllBytes();
        var knightCharacter = objectMapper.readValue(knightCharacterAsBytes, Character.class);
        var druidCharacter = objectMapper.readValue(druidCharacterAsBytes, Character.class);

        wiremock.stubFor(get(urlEqualTo("/account"))
                .willReturn(aResponse().withStatus(200)
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody(accountAsBytes)));

        //act
        var characters = characterController.getCharacters();

        //assert
        assertThat(characters).hasSize(2);
        assertThat(characters.get(0)).isEqualTo(knightCharacter);
        assertThat(characters.get(1)).isEqualTo(druidCharacter);
    }

    @Test
    public void shouldReturnEmptyListOfCharacters() throws IOException {

        //arrange
        var account = this.getClass().getResourceAsStream("/json/account/accountWithoutCharacter.json").readAllBytes();
        wiremock.stubFor(get(urlEqualTo("/account"))
                .willReturn(aResponse().withStatus(200)
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody(account)));

        //act
        var characters = characterController.getCharacters();

        //assert
        assertThat(characters).hasSize(0);
    }

    @Test
    public void shouldAddNewCharacter() throws IOException {

        //arrange
        var accountAsBytes = this.getClass().getResourceAsStream("/json/account/accountWithoutCharacter.json").readAllBytes();
        var objectMapper = new ObjectMapper();
        var account = objectMapper.readValue(accountAsBytes, Account.class);
        wiremock.stubFor(get(urlEqualTo("/account"))
                .willReturn(aResponse().withStatus(200)
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody(accountAsBytes)));
        var character = new Character("CharacterName", CharacterClass.KNIGHT, account);

        //act
        characterController.addCharacter(character);

        //assert
        var characters = characterRepository.findByAccount(character.getAccount());
        assertThat(characters).hasSize(1);
        assertThat(characters.get(0)).isEqualTo(character);
    }
}
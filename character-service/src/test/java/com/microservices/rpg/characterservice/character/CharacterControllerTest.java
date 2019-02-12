package com.microservices.rpg.characterservice.character;

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
    public void setup() {
        dbMigration = new DbMigration();
        dbMigration.start();
    }

    @Test
    public void shouldReturnCharacters() {

        //arrange
        wiremock.stubFor(get(urlEqualTo("/account"))
                .willReturn(aResponse().withStatus(200)
                        .withHeader(HttpHeaders.CONTENT_TYPE,
                                MediaType.APPLICATION_JSON_VALUE)
                        .withBody("{ \"id\": \"00uj41y0cei3z4hhh0h7\", \"name\": \"Arkadiusz Szast\", \"email\": \"szastarek@live.com\" }")));

        //act
        var characters = characterController.getCharacters();

        //asserta
        assertThat(characters).hasSize(2);
        assertThat(characters.get(0).getId()).isEqualTo("5c61c2cb6790df5ac495fa71");
        assertThat(characters.get(1).getId()).isEqualTo("5c61c2cb6790df5ac495fa72");
    }

    @Test
    public void shouldReturnEmptyListOfCharacters() {

        //arrange
        wiremock.stubFor(get(urlEqualTo("/account"))
                .willReturn(aResponse().withStatus(200)
                        .withHeader(HttpHeaders.CONTENT_TYPE,
                                MediaType.APPLICATION_JSON_VALUE)
                        .withBody("{ \"id\": \"00uj41y0cei3z4hhh0g2\", \"name\": \"Joe Doe\", \"email\": \"joeDoe@mail.com\" }")));

        //act
        var characters = characterController.getCharacters();

        //assert
        assertThat(characters).hasSize(0);
    }

    @Test
    public void shouldAddNewCharacter() {

        //arrange
        wiremock.stubFor(get(urlEqualTo("/account"))
                .willReturn(aResponse().withStatus(200)
                        .withHeader(HttpHeaders.CONTENT_TYPE,
                                MediaType.APPLICATION_JSON_VALUE)
                        .withBody("{ \"id\": \"00uj41y0cei3z4hhh0g2\", \"name\": \"Joe Doe\", \"email\": \"joeDoe@mail.com\" }")));
        var character = new Character("CharacterName", CharacterClass.KNIGHT, null);

        //act
        characterController.addCharacter(character);

        //assert
        var characters = characterRepository.findByAccount(character.getAccount());
        assertThat(characters).hasSize(1);
        assertThat(characters.get(0)).isEqualTo(character);
    }
}
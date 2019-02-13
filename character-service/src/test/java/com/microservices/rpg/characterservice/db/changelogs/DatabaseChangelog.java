package com.microservices.rpg.characterservice.db.changelogs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.microservices.rpg.characterservice.character.domain.Character;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.IOException;
import java.util.List;

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "clear_character_collection", author = "arkadiusz.szast", runAlways = true)
    public void clearCharacterCollection() {
        mongoTemplate().dropCollection("character");
        mongoTemplate().createCollection("character");
    }

    @ChangeSet(order = "002", id = "add_test_characters", author = "arkadiusz.szast", runAlways = true)
    public void addExampleCharacters() throws IOException {
        var objectMapper = new ObjectMapper();
        var knightCharacterAsBytes = this.getClass().getResourceAsStream("/json/character/knightCharacter.json").readAllBytes();
        var druidCharacterAsBytes = this.getClass().getResourceAsStream("/json/character/druidCharacter.json").readAllBytes();

        var knightCharacter = objectMapper.readValue(knightCharacterAsBytes, Character.class);
        var druidCharacter = objectMapper.readValue(druidCharacterAsBytes, Character.class);
        mongoTemplate().insertAll(List.of(knightCharacter, druidCharacter));
    }

    private MongoClient mongo() {
        return new MongoClient(new MongoClientURI("mongodb://mongoadmin:secret@localhost:27014/character?authSource=admin"));
    }

    private MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongo(), "character");
    }
}

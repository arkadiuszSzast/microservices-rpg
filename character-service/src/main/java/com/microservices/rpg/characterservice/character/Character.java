package com.microservices.rpg.characterservice.character;

import com.microservices.rpg.characterservice.account.Account;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Character {

    @Id
    private String id;
    private String name;
    private CharacterClass characterClass;
    private Account account;

    public Character() {
    }

    public Character(String name, CharacterClass characterClass, Account account) {
        this.name = name;
        this.characterClass = characterClass;
        this.account = account;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CharacterClass getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(CharacterClass characterClass) {
        this.characterClass = characterClass;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}

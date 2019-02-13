package com.microservices.rpg.characterservice.character.domain;

import com.microservices.rpg.characterservice.account.Account;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document
public class Character {

    @Id
    private String id;
    private String name;
    private CharacterClass characterClass;
    private Account account;

    public Character() {
    }

    public Character(String id, String name, CharacterClass characterClass, Account account) {
        this.id = id;
        this.name = name;
        this.characterClass = characterClass;
        this.account = account;
    }

    public Character(String name, CharacterClass characterClass, Account account) {
        this.name = name;
        this.characterClass = characterClass;
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character character = (Character) o;
        return Objects.equals(id, character.id) &&
                Objects.equals(name, character.name) &&
                characterClass == character.characterClass &&
                Objects.equals(account, character.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, characterClass, account);
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

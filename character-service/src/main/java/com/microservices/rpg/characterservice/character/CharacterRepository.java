package com.microservices.rpg.characterservice.character;

import com.microservices.rpg.characterservice.account.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CharacterRepository extends MongoRepository<Character, String> {
    List<Character> findByAccount(Account account);
}

package com.microservices.rpg.characterservice.character.domain;

import com.microservices.rpg.characterservice.account.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends MongoRepository<Character, String> {
    List<Character> findByAccount(Account account);
}

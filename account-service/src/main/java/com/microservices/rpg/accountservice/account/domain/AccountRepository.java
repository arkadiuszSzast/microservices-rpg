package com.microservices.rpg.accountservice.account.domain;

import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.MongoRepository;

@EnableMongoAuditing
public interface AccountRepository extends MongoRepository<Account, String> {
}

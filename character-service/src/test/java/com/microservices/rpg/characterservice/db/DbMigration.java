package com.microservices.rpg.characterservice.db;

import net.ozwolf.mongo.migrations.MongoTrek;
import net.ozwolf.mongo.migrations.MongoTrekState;
import net.ozwolf.mongo.migrations.exception.MongoTrekFailureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DbMigration {
    private final static Logger LOGGER = LoggerFactory.getLogger(DbMigration.class);

    public void start() {
        try {
            MongoTrek trek = new MongoTrek("db/characterMigration_1_0_0.yml", "mongodb://mongoadmin:secret@localhost:27014/character?authSource=admin");

            MongoTrekState state = trek.migrate();

            LOGGER.info("Successfully migrated schema to version: " + state.getCurrentVersion());
        } catch (MongoTrekFailureException e) {
            LOGGER.error("Failed to migrate database", e);

            System.exit(-1);
        }
    }
}

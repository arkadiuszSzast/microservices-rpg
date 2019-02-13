package com.microservices.rpg.characterservice.db;

import com.github.mongobee.Mongobee;
import com.github.mongobee.exception.MongobeeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DbMigration {
    private final static Logger LOGGER = LoggerFactory.getLogger(DbMigration.class);


    public void execute() throws MongobeeException {
        Mongobee runner = new Mongobee("mongodb://mongoadmin:secret@localhost:27014/character?authSource=admin");
        runner.setDbName("character");
        runner.setChangeLogsScanPackage("com.microservices.rpg.characterservice.db.changelogs");
        runner.execute();
    }
}

package com.microservices.rpg.characterservice.account;

import org.springframework.data.mongodb.core.mapping.Field;

public class Account {

    @Field("id")
    private String id;
    private String name;
    private String email;

    public Account(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Account() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

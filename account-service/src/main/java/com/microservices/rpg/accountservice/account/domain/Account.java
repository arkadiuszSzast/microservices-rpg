package com.microservices.rpg.accountservice.account.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public class Account {

    private String id;
    private String name;
    private String email;
    private LocalDateTime createdDate;
    private LocalDateTime lastLogInDate;

    public Account() {
    }

    public Account(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        createdDate = LocalDateTime.now();
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

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getLastLogInDate() {
        return lastLogInDate;
    }

    public void setLastLogInDate(LocalDateTime lastLogInDate) {
        this.lastLogInDate = lastLogInDate;
    }
}

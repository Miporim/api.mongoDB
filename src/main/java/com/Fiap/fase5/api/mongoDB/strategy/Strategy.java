package com.Fiap.fase5.api.mongoDB.strategy;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "strategies")
public class Strategy {

    @Id
    private String id;

    private String title;
    private String description;

    private String userCreator;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Strategy() {
    }

    public Strategy(
            String title,
            String description,
            String userCreator,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.title = title;
        this.description = description;
        this.userCreator = userCreator;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUserCreator() {
        return userCreator;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void update(String title, String description) {
        this.title = title;
        this.description = description;
        this.updatedAt = LocalDateTime.now();
    }
}
package com.Fiap.fase5.api.mongoDB.orientation;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "orientations")
public class Orientation {
    @Id private String id;
    private String title, description, userCreator;
    private long createdAt, updatedAt;
    public Orientation() { }
    public Orientation(String title, String description, String userCreator) { this.title = title; this.description = description; this.userCreator = userCreator; this.createdAt = System.currentTimeMillis(); this.updatedAt = this.createdAt; }
    public String getId() { return id; } public String getTitle() { return title; } public String getDescription() { return description; } public String getUserCreator() { return userCreator; } public long getCreatedAt() { return createdAt; } public long getUpdatedAt() { return updatedAt; }
    public void update(String title, String description) { this.title = title; this.description = description; this.updatedAt = System.currentTimeMillis(); }
}

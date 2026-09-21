package com.Fiap.fase5.api.mongoDB.idea;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ideas")
public class Idea {
    @Id private String id;
    private String title;
    private String description;
    private String userCreator;
    private int scoreTotal;
    private int totalVotes;
    private long createdAt;
    private long updatedAt;
    public Idea() { }
    public Idea(String title, String description, String userCreator) { this.title = title; this.description = description; this.userCreator = userCreator; this.createdAt = System.currentTimeMillis(); this.updatedAt = this.createdAt; }
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getUserCreator() { return userCreator; }
    public int getScoreTotal() { return scoreTotal; }
    public int getTotalVotes() { return totalVotes; }
    public long getCreatedAt() { return createdAt; }
    public long getUpdatedAt() { return updatedAt; }
    public void update(String title, String description) { this.title = title; this.description = description; this.updatedAt = System.currentTimeMillis(); }
    public void updateVoteSummary(int scoreTotal, int totalVotes) { this.scoreTotal = scoreTotal; this.totalVotes = totalVotes; this.updatedAt = System.currentTimeMillis(); }
}

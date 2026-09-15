package com.Fiap.fase5.api.mongoDB.idea;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ideas")
public class Idea {

    @Id
    private String id;
    private String title;
    private String description;
    private String strategyId;
    private String createdByEmail;
    private IdeaStatus status;

    public Idea() {
    }

    public Idea(String title, String description, String strategyId, String createdByEmail) {
        this.title = title;
        this.description = description;
        this.strategyId = strategyId;
        this.createdByEmail = createdByEmail;
        this.status = IdeaStatus.PENDENTE;
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

    public String getStrategyId() {
        return strategyId;
    }

    public String getCreatedByEmail() {
        return createdByEmail;
    }

    public IdeaStatus getStatus() {
        return status;
    }

    public void changeStatus(IdeaStatus status) {
        this.status = status;
    }
}

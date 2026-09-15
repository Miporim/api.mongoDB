package com.Fiap.fase5.api.mongoDB.strategy;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "strategies")
public class Strategy {

    @Id
    private String id;
    private String title;
    private String description;

    public Strategy() {
    }

    public Strategy(String title, String description) {
        this.title = title;
        this.description = description;
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

    public void update(String title, String description) {
        this.title = title;
        this.description = description;
    }
}

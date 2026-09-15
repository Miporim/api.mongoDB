package com.Fiap.fase5.api.mongoDB.vote;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "priority_votes")
@CompoundIndex(name = "unique_vote_per_idea_and_manager", def = "{'ideaId': 1, 'voterEmail': 1}", unique = true)
public class PriorityVote {

    @Id
    private String id;
    private String ideaId;
    private String voterEmail;
    private int score;

    public PriorityVote() {
    }

    public PriorityVote(String ideaId, String voterEmail, int score) {
        this.ideaId = ideaId;
        this.voterEmail = voterEmail;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public String getIdeaId() {
        return ideaId;
    }

    public String getVoterEmail() {
        return voterEmail;
    }

    public int getScore() {
        return score;
    }
}

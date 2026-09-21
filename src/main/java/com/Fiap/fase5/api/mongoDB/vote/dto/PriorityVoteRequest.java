package com.Fiap.fase5.api.mongoDB.vote.dto;

import jakarta.validation.constraints.AssertTrue;

public record PriorityVoteRequest(
        int score
) {
    @AssertTrue(message = "A pontuação deve ser 1 ou -1")
    public boolean isValidScore() { return score == 1 || score == -1; }
}

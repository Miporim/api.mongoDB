package com.Fiap.fase5.api.mongoDB.vote.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record PriorityVoteRequest(
        @Min(value = 1, message = "A pontuação mínima é 1")
        @Max(value = 5, message = "A pontuação máxima é 5")
        int score
) {
}

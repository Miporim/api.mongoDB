package com.Fiap.fase5.api.mongoDB.idea.dto;

import jakarta.validation.constraints.NotBlank;

public record IdeaRequest(
        @NotBlank(message = "Título é obrigatório") String title,
        @NotBlank(message = "Descrição é obrigatória") String description,
        @NotBlank(message = "Estratégia é obrigatória") String strategyId
) {
}

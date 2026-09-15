package com.Fiap.fase5.api.mongoDB.strategy.dto;

import jakarta.validation.constraints.NotBlank;

public record StrategyRequest(
        @NotBlank(message = "Título é obrigatório") String title,
        @NotBlank(message = "Descrição é obrigatória") String description
) {
}

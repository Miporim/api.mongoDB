package com.Fiap.fase5.api.mongoDB.idea.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;

@JsonIgnoreProperties(ignoreUnknown = true)
public record IdeaRequest(
        @NotBlank(message = "Título é obrigatório") String title,
        @NotBlank(message = "Descrição é obrigatória") String description
) {
}

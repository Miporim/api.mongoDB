package com.Fiap.fase5.api.mongoDB.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ProjectRequest(@NotBlank String title, @NotBlank String description,
                             @PositiveOrZero double investimento, @PositiveOrZero double receita,
                             long prazoInicial, long dataConclusao, boolean projetoEncerrado) { }

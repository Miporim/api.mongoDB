package com.Fiap.fase5.api.mongoDB.project.dto;

import com.Fiap.fase5.api.mongoDB.project.ProjectStage;
import com.Fiap.fase5.api.mongoDB.project.ProjectStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDate;

public record ProjectRequest(@NotBlank String title, @NotBlank String description, @NotBlank String ideaId,
                             @NotNull ProjectStatus status, @NotNull ProjectStage stage,
                             @NotNull @PositiveOrZero BigDecimal investment, @NotNull @PositiveOrZero BigDecimal revenue,
                             @NotNull @PositiveOrZero BigDecimal costReduction, @NotNull @PositiveOrZero BigDecimal productivity,
                             @NotNull LocalDate deadline) { }

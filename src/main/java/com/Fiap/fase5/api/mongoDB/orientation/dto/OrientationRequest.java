package com.Fiap.fase5.api.mongoDB.orientation.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
@JsonIgnoreProperties(ignoreUnknown = true)
public record OrientationRequest(@NotBlank String title, @NotBlank String description) { }

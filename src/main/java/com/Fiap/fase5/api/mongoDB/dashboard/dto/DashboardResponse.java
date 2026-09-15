package com.Fiap.fase5.api.mongoDB.dashboard.dto;

import java.math.BigDecimal;

public record DashboardResponse(
        BigDecimal investment,
        BigDecimal revenue,
        BigDecimal profit,
        BigDecimal roi,
        BigDecimal costReduction,
        BigDecimal productivity
) {
}

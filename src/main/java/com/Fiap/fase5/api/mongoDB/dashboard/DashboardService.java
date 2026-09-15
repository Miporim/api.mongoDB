package com.Fiap.fase5.api.mongoDB.dashboard;

import com.Fiap.fase5.api.mongoDB.dashboard.dto.DashboardResponse;
import com.Fiap.fase5.api.mongoDB.project.Project;
import com.Fiap.fase5.api.mongoDB.project.ProjectRepository;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {
    private static final BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100);
    private final ProjectRepository projectRepository;
    public DashboardService(ProjectRepository projectRepository) { this.projectRepository = projectRepository; }

    public DashboardResponse summarize() {
        List<Project> projects = projectRepository.findAll();
        BigDecimal investment = sum(projects, Project::getInvestment);
        BigDecimal revenue = sum(projects, Project::getRevenue);
        BigDecimal costReduction = sum(projects, Project::getCostReduction);
        BigDecimal productivity = sum(projects, Project::getProductivity);
        BigDecimal profit = revenue.subtract(investment);
        BigDecimal roi = investment.signum() == 0 ? BigDecimal.ZERO
                : profit.multiply(ONE_HUNDRED).divide(investment, 2, RoundingMode.HALF_UP);
        return new DashboardResponse(investment, revenue, profit, roi, costReduction, productivity);
    }

    private BigDecimal sum(List<Project> projects, java.util.function.Function<Project, BigDecimal> value) {
        return projects.stream().map(value).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

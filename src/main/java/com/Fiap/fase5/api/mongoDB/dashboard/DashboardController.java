package com.Fiap.fase5.api.mongoDB.dashboard;

import com.Fiap.fase5.api.mongoDB.dashboard.dto.DashboardResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    private final DashboardService dashboardService;
    public DashboardController(DashboardService dashboardService) { this.dashboardService = dashboardService; }
    @GetMapping
    @PreAuthorize("hasRole('LIDER')")
    public DashboardResponse summarize() { return dashboardService.summarize(); }
}

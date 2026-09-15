package com.Fiap.fase5.api.mongoDB.project;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "projects")
public class Project {
    @Id private String id;
    private String title;
    private String description;
    private String ideaId;
    private ProjectStatus status;
    private ProjectStage stage;
    private BigDecimal investment;
    private BigDecimal revenue;
    private BigDecimal costReduction;
    private BigDecimal productivity;
    private LocalDate deadline;
    public Project() { }
    public Project(String title, String description, String ideaId, ProjectStatus status, ProjectStage stage, BigDecimal investment, BigDecimal revenue, BigDecimal costReduction, BigDecimal productivity, LocalDate deadline) { update(title, description, ideaId, status, stage, investment, revenue, costReduction, productivity, deadline); }
    public void update(String title, String description, String ideaId, ProjectStatus status, ProjectStage stage, BigDecimal investment, BigDecimal revenue, BigDecimal costReduction, BigDecimal productivity, LocalDate deadline) { this.title=title; this.description=description; this.ideaId=ideaId; this.status=status; this.stage=stage; this.investment=investment; this.revenue=revenue; this.costReduction=costReduction; this.productivity=productivity; this.deadline=deadline; }
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getIdeaId() { return ideaId; }
    public ProjectStatus getStatus() { return status; }
    public ProjectStage getStage() { return stage; }
    public BigDecimal getInvestment() { return investment; }
    public BigDecimal getRevenue() { return revenue; }
    public BigDecimal getCostReduction() { return costReduction; }
    public BigDecimal getProductivity() { return productivity; }
    public LocalDate getDeadline() { return deadline; }
}

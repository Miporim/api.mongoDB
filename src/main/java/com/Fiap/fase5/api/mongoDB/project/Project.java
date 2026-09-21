package com.Fiap.fase5.api.mongoDB.project;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "projects")
public class Project {
    @Id private String id;
    private String title, description, userCreator;
    private double investimento, receita;
    private long prazoInicial, dataConclusao, createdAt, updatedAt;
    private boolean projetoEncerrado;
    public Project() { }
    public Project(String title, String description, String userCreator, double investimento, double receita, long prazoInicial, long dataConclusao, boolean projetoEncerrado) { update(title, description, investimento, receita, prazoInicial, dataConclusao, projetoEncerrado); this.userCreator = userCreator; this.createdAt = System.currentTimeMillis(); this.updatedAt = this.createdAt; }
    public String getId() { return id; } public String getTitle() { return title; } public String getDescription() { return description; } public String getUserCreator() { return userCreator; } public double getInvestimento() { return investimento; } public double getReceita() { return receita; } public long getPrazoInicial() { return prazoInicial; } public long getDataConclusao() { return dataConclusao; } public boolean isProjetoEncerrado() { return projetoEncerrado; } public long getCreatedAt() { return createdAt; } public long getUpdatedAt() { return updatedAt; }
    public void update(String title, String description, double investimento, double receita, long prazoInicial, long dataConclusao, boolean projetoEncerrado) { this.title = title; this.description = description; this.investimento = investimento; this.receita = receita; this.prazoInicial = prazoInicial; this.dataConclusao = dataConclusao; this.projetoEncerrado = projetoEncerrado; this.updatedAt = System.currentTimeMillis(); }
}

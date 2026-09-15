package com.Fiap.fase5.api.mongoDB.audit;

import java.time.Instant;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "audit_logs")
public class AuditLog {
    @Id private String id;
    private String action;
    private String resource;
    private String resourceId;
    private String userEmail;
    private Instant createdAt;
    public AuditLog() { }
    public AuditLog(String action, String resource, String resourceId, String userEmail) { this.action=action; this.resource=resource; this.resourceId=resourceId; this.userEmail=userEmail; this.createdAt=Instant.now(); }
    public String getId() { return id; }
    public String getAction() { return action; }
    public String getResource() { return resource; }
    public String getResourceId() { return resourceId; }
    public String getUserEmail() { return userEmail; }
    public Instant getCreatedAt() { return createdAt; }
}

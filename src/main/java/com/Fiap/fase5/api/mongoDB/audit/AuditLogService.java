package com.Fiap.fase5.api.mongoDB.audit;

import org.springframework.stereotype.Service;

@Service
public class AuditLogService {
    private final AuditLogRepository auditLogRepository;
    public AuditLogService(AuditLogRepository auditLogRepository) { this.auditLogRepository = auditLogRepository; }
    public void register(String action, String resource, String resourceId, String userEmail) { auditLogRepository.save(new AuditLog(action, resource, resourceId, userEmail)); }
}

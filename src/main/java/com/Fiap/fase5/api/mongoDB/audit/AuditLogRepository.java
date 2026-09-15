package com.Fiap.fase5.api.mongoDB.audit;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuditLogRepository extends MongoRepository<AuditLog, String> { }

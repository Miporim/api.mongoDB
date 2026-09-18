package com.Fiap.fase5.api.mongoDB.strategy;

import com.Fiap.fase5.api.mongoDB.audit.AuditLogService;
import com.Fiap.fase5.api.mongoDB.strategy.dto.StrategyRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StrategyService {

    private final StrategyRepository strategyRepository;
    private final AuditLogService auditLogService;

    public StrategyService(
            StrategyRepository strategyRepository,
            AuditLogService auditLogService
    ) {
        this.strategyRepository = strategyRepository;
        this.auditLogService = auditLogService;
    }

    public Strategy create(StrategyRequest request, String userEmail) {

        LocalDateTime now = LocalDateTime.now();

        Strategy strategy = new Strategy(
                request.title().trim(),
                request.description().trim(),
                userEmail,
                now,
                now
        );

        Strategy savedStrategy = strategyRepository.save(strategy);

        auditLogService.register(
                "CREATE",
                "STRATEGY",
                savedStrategy.getId(),
                userEmail
        );

        return savedStrategy;
    }

    public List<Strategy> findAll() {
        return strategyRepository.findAll();
    }

    public Strategy findById(String id) {
        return strategyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Orientação estratégica não encontrada"
                ));
    }

    public Strategy update(
            String id,
            StrategyRequest request,
            String userEmail
    ) {

        Strategy strategy = findById(id);

        strategy.update(
                request.title().trim(),
                request.description().trim()
        );

        Strategy savedStrategy = strategyRepository.save(strategy);

        auditLogService.register(
                "UPDATE",
                "STRATEGY",
                id,
                userEmail
        );

        return savedStrategy;
    }

    public void delete(String id, String userEmail) {

        Strategy strategy = findById(id);

        strategyRepository.delete(strategy);

        auditLogService.register(
                "DELETE",
                "STRATEGY",
                id,
                userEmail
        );
    }
}
package com.Fiap.fase5.api.mongoDB.idea;

import com.Fiap.fase5.api.mongoDB.idea.dto.IdeaRequest;
import com.Fiap.fase5.api.mongoDB.audit.AuditLogService;
import com.Fiap.fase5.api.mongoDB.strategy.StrategyRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class IdeaService {

    private final IdeaRepository ideaRepository;
    private final StrategyRepository strategyRepository;
    private final AuditLogService auditLogService;

    public IdeaService(IdeaRepository ideaRepository, StrategyRepository strategyRepository, AuditLogService auditLogService) {
        this.ideaRepository = ideaRepository;
        this.strategyRepository = strategyRepository;
        this.auditLogService = auditLogService;
    }

    public Idea create(IdeaRequest request, String creatorEmail) {
        if (!strategyRepository.existsById(request.strategyId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Orientação estratégica não encontrada");
        }

        Idea idea = new Idea(
                request.title().trim(),
                request.description().trim(),
                request.strategyId(),
                creatorEmail
        );
        Idea savedIdea = ideaRepository.save(idea);
        auditLogService.register("CREATE", "IDEA", savedIdea.getId(), creatorEmail);
        return savedIdea;
    }

    public List<Idea> findAll(Authentication authentication) {
        if (hasRole(authentication, "GESTOR")) {
            return ideaRepository.findAll();
        }
        return ideaRepository.findByCreatedByEmail(authentication.getName());
    }

    public Idea findById(String id, Authentication authentication) {
        Idea idea = ideaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ideia não encontrada"));

        if (!hasRole(authentication, "GESTOR") && !idea.getCreatedByEmail().equals(authentication.getName())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Você não pode consultar esta ideia");
        }
        return idea;
    }

    public Idea changeStatus(String id, IdeaStatus status, String userEmail) {
        Idea idea = ideaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ideia não encontrada"));
        idea.changeStatus(status);
        Idea savedIdea = ideaRepository.save(idea);
        auditLogService.register(status == IdeaStatus.APROVADA ? "APPROVE" : "REJECT", "IDEA", id, userEmail);
        return savedIdea;
    }

    private boolean hasRole(Authentication authentication, String role) {
        return authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_" + role));
    }
}

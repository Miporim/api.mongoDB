package com.Fiap.fase5.api.mongoDB.idea;

import com.Fiap.fase5.api.mongoDB.audit.AuditLogService;
import com.Fiap.fase5.api.mongoDB.idea.dto.IdeaRequest;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class IdeaService {
    private final IdeaRepository ideaRepository;
    private final AuditLogService auditLogService;
    public IdeaService(IdeaRepository ideaRepository, AuditLogService auditLogService) { this.ideaRepository = ideaRepository; this.auditLogService = auditLogService; }
    public Idea create(IdeaRequest request, String userEmail) { Idea saved = ideaRepository.save(new Idea(request.title().trim(), request.description().trim(), userEmail)); auditLogService.register("CREATE", "IDEA", saved.getId(), userEmail); return saved; }
    public List<Idea> findAll(Authentication auth) { return hasRole(auth, "GESTOR") || hasRole(auth, "LIDER") ? ideaRepository.findAll() : ideaRepository.findByUserCreator(auth.getName()); }
    public Idea findById(String id, Authentication auth) { Idea idea = find(id); if (!canRead(idea, auth)) throw forbidden(); return idea; }
    public Idea update(String id, IdeaRequest request, String userEmail) { Idea idea = find(id); ensureOwnerWithoutVotes(idea, userEmail); idea.update(request.title().trim(), request.description().trim()); Idea saved = ideaRepository.save(idea); auditLogService.register("UPDATE", "IDEA", id, userEmail); return saved; }
    public void delete(String id, String userEmail) { Idea idea = find(id); ensureOwnerWithoutVotes(idea, userEmail); ideaRepository.delete(idea); auditLogService.register("DELETE", "IDEA", id, userEmail); }
    public Idea updateVoteSummary(String id, int scoreTotal, int totalVotes) { Idea idea = find(id); idea.updateVoteSummary(scoreTotal, totalVotes); return ideaRepository.save(idea); }
    private Idea find(String id) { return ideaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ideia não encontrada")); }
    private boolean canRead(Idea idea, Authentication auth) { return hasRole(auth, "GESTOR") || hasRole(auth, "LIDER") || idea.getUserCreator().equals(auth.getName()); }
    private void ensureOwnerWithoutVotes(Idea idea, String userEmail) { if (!idea.getUserCreator().equals(userEmail)) throw forbidden(); if (idea.getTotalVotes() > 0) throw new ResponseStatusException(HttpStatus.CONFLICT, "Ideias avaliadas não podem ser alteradas"); }
    private ResponseStatusException forbidden() { return new ResponseStatusException(HttpStatus.FORBIDDEN, "Você não tem permissão para esta ideia"); }
    private boolean hasRole(Authentication auth, String role) { return auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_" + role)); }
}

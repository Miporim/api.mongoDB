package com.Fiap.fase5.api.mongoDB.project;

import com.Fiap.fase5.api.mongoDB.audit.AuditLogService;
import com.Fiap.fase5.api.mongoDB.project.dto.ProjectRequest;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository; private final AuditLogService auditLogService;
    public ProjectService(ProjectRepository projectRepository, AuditLogService auditLogService) { this.projectRepository = projectRepository; this.auditLogService = auditLogService; }
    public Project create(ProjectRequest r, String email) { Project saved = projectRepository.save(new Project(r.title().trim(), r.description().trim(), email, r.investimento(), r.receita(), r.prazoInicial(), r.dataConclusao(), r.projetoEncerrado())); auditLogService.register("CREATE", "PROJECT", saved.getId(), email); return saved; }
    public List<Project> findAll(Authentication auth) { return hasRole(auth, "LIDER") ? projectRepository.findAll() : projectRepository.findByUserCreator(auth.getName()); }
    public Project findById(String id, Authentication auth) { Project project = find(id); if (!hasRole(auth, "LIDER") && !project.getUserCreator().equals(auth.getName())) throw forbidden(); return project; }
    public Project update(String id, ProjectRequest r, String email) { Project p = find(id); ensureOwner(p, email); p.update(r.title().trim(), r.description().trim(), r.investimento(), r.receita(), r.prazoInicial(), r.dataConclusao(), r.projetoEncerrado()); Project saved = projectRepository.save(p); auditLogService.register("UPDATE", "PROJECT", id, email); return saved; }
    public void delete(String id, String email) { Project p = find(id); ensureOwner(p, email); projectRepository.delete(p); auditLogService.register("DELETE", "PROJECT", id, email); }
    private Project find(String id) { return projectRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Projeto não encontrado")); }
    private void ensureOwner(Project p, String email) { if (!p.getUserCreator().equals(email)) throw forbidden(); }
    private ResponseStatusException forbidden() { return new ResponseStatusException(HttpStatus.FORBIDDEN, "Você não tem permissão para este projeto"); }
    private boolean hasRole(Authentication auth, String role) { return auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_" + role)); }
}

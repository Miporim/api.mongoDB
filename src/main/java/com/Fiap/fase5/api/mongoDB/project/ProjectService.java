package com.Fiap.fase5.api.mongoDB.project;

import com.Fiap.fase5.api.mongoDB.idea.IdeaRepository;
import com.Fiap.fase5.api.mongoDB.project.dto.ProjectRequest;
import com.Fiap.fase5.api.mongoDB.audit.AuditLogService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final IdeaRepository ideaRepository;
    private final AuditLogService auditLogService;
    public ProjectService(ProjectRepository projectRepository, IdeaRepository ideaRepository, AuditLogService auditLogService) { this.projectRepository=projectRepository; this.ideaRepository=ideaRepository; this.auditLogService=auditLogService; }
    public Project create(ProjectRequest r, String userEmail) { ensureIdea(r.ideaId()); Project project=projectRepository.save(new Project(r.title().trim(),r.description().trim(),r.ideaId(),r.status(),r.stage(),r.investment(),r.revenue(),r.costReduction(),r.productivity(),r.deadline())); auditLogService.register("CREATE","PROJECT",project.getId(),userEmail); return project; }
    public List<Project> findAll() { return projectRepository.findAll(); }
    public Project findById(String id) { return projectRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Projeto não encontrado")); }
    public Project update(String id, ProjectRequest r, String userEmail) { ensureIdea(r.ideaId()); Project p=findById(id); p.update(r.title().trim(),r.description().trim(),r.ideaId(),r.status(),r.stage(),r.investment(),r.revenue(),r.costReduction(),r.productivity(),r.deadline()); Project project=projectRepository.save(p); auditLogService.register("UPDATE","PROJECT",id,userEmail); return project; }
    private void ensureIdea(String id) { if (!ideaRepository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Ideia não encontrada"); }
}

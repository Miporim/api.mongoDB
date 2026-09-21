package com.Fiap.fase5.api.mongoDB.project;

import com.Fiap.fase5.api.mongoDB.project.dto.ProjectRequest;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectService projectService; public ProjectController(ProjectService projectService) { this.projectService = projectService; }
    @PostMapping @PreAuthorize("hasRole('GESTOR')") public ResponseEntity<Project> create(@Valid @RequestBody ProjectRequest r, Authentication a) { Project p = projectService.create(r, a.getName()); return ResponseEntity.created(URI.create("/api/projects/" + p.getId())).body(p); }
    @GetMapping @PreAuthorize("hasAnyRole('GESTOR', 'LIDER')") public List<Project> findAll(Authentication a) { return projectService.findAll(a); }
    @GetMapping("/{id}") @PreAuthorize("hasAnyRole('GESTOR', 'LIDER')") public Project findById(@PathVariable String id, Authentication a) { return projectService.findById(id, a); }
    @PutMapping("/{id}") @PreAuthorize("hasRole('GESTOR')") public Project update(@PathVariable String id, @Valid @RequestBody ProjectRequest r, Authentication a) { return projectService.update(id, r, a.getName()); }
    @DeleteMapping("/{id}") @PreAuthorize("hasRole('GESTOR')") public ResponseEntity<Void> delete(@PathVariable String id, Authentication a) { projectService.delete(id, a.getName()); return ResponseEntity.noContent().build(); }
}

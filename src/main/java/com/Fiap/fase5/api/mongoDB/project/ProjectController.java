package com.Fiap.fase5.api.mongoDB.project;

import com.Fiap.fase5.api.mongoDB.project.dto.ProjectRequest;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectService projectService;
    public ProjectController(ProjectService projectService) { this.projectService=projectService; }
    @PostMapping @PreAuthorize("hasRole('GESTOR')")
    public ResponseEntity<Project> create(@Valid @RequestBody ProjectRequest request, Authentication authentication) { Project p=projectService.create(request, authentication.getName()); return ResponseEntity.created(URI.create("/api/projects/"+p.getId())).body(p); }
    @GetMapping public List<Project> findAll() { return projectService.findAll(); }
    @GetMapping("/{id}") public Project findById(@PathVariable String id) { return projectService.findById(id); }
    @PutMapping("/{id}") @PreAuthorize("hasRole('GESTOR')")
    public Project update(@PathVariable String id, @Valid @RequestBody ProjectRequest request, Authentication authentication) { return projectService.update(id,request,authentication.getName()); }
}

package com.Fiap.fase5.api.mongoDB.idea;

import com.Fiap.fase5.api.mongoDB.idea.dto.IdeaRequest;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ideas")
public class IdeaController {
    private final IdeaService ideaService;
    public IdeaController(IdeaService ideaService) { this.ideaService = ideaService; }
    @PostMapping @PreAuthorize("hasRole('OPERADOR')")
    public ResponseEntity<Idea> create(@Valid @RequestBody IdeaRequest request, Authentication auth) { Idea idea = ideaService.create(request, auth.getName()); return ResponseEntity.created(URI.create("/api/ideas/" + idea.getId())).body(idea); }
    @GetMapping @PreAuthorize("hasAnyRole('OPERADOR', 'GESTOR', 'LIDER')")
    public List<Idea> findAll(Authentication auth) { return ideaService.findAll(auth); }
    @GetMapping("/{id}") @PreAuthorize("hasAnyRole('OPERADOR', 'GESTOR', 'LIDER')")
    public Idea findById(@PathVariable String id, Authentication auth) { return ideaService.findById(id, auth); }
    @PutMapping("/{id}") @PreAuthorize("hasRole('OPERADOR')")
    public Idea update(@PathVariable String id, @Valid @RequestBody IdeaRequest request, Authentication auth) { return ideaService.update(id, request, auth.getName()); }
    @DeleteMapping("/{id}") @PreAuthorize("hasRole('OPERADOR')")
    public ResponseEntity<Void> delete(@PathVariable String id, Authentication auth) { ideaService.delete(id, auth.getName()); return ResponseEntity.noContent().build(); }
}

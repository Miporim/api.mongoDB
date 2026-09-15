package com.Fiap.fase5.api.mongoDB.idea;

import com.Fiap.fase5.api.mongoDB.idea.dto.IdeaRequest;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ideas")
public class IdeaController {

    private final IdeaService ideaService;

    public IdeaController(IdeaService ideaService) {
        this.ideaService = ideaService;
    }

    @PostMapping
    @PreAuthorize("hasRole('OPERADOR')")
    public ResponseEntity<Idea> create(@Valid @RequestBody IdeaRequest request, Authentication authentication) {
        Idea idea = ideaService.create(request, authentication.getName());
        return ResponseEntity.created(URI.create("/api/ideas/" + idea.getId())).body(idea);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('OPERADOR', 'GESTOR')")
    public List<Idea> findAll(Authentication authentication) {
        return ideaService.findAll(authentication);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('OPERADOR', 'GESTOR')")
    public Idea findById(@PathVariable String id, Authentication authentication) {
        return ideaService.findById(id, authentication);
    }

    @PatchMapping("/{id}/approve")
    @PreAuthorize("hasRole('GESTOR')")
    public Idea approve(@PathVariable String id, Authentication authentication) {
        return ideaService.changeStatus(id, IdeaStatus.APROVADA, authentication.getName());
    }

    @PatchMapping("/{id}/reject")
    @PreAuthorize("hasRole('GESTOR')")
    public Idea reject(@PathVariable String id, Authentication authentication) {
        return ideaService.changeStatus(id, IdeaStatus.REJEITADA, authentication.getName());
    }
}

package com.Fiap.fase5.api.mongoDB.strategy;

import com.Fiap.fase5.api.mongoDB.strategy.dto.StrategyRequest;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/api/strategies")
public class StrategyController {

    private final StrategyService strategyService;

    public StrategyController(StrategyService strategyService) {
        this.strategyService = strategyService;
    }

    @PostMapping
    @PreAuthorize("hasRole('LIDER')")
    public ResponseEntity<Strategy> create(@Valid @RequestBody StrategyRequest request, Authentication authentication) {
        Strategy strategy = strategyService.create(request, authentication.getName());
        return ResponseEntity.created(URI.create("/api/strategies/" + strategy.getId())).body(strategy);
    }

    @GetMapping
    public List<Strategy> findAll() {
        return strategyService.findAll();
    }

    @GetMapping("/{id}")
    public Strategy findById(@PathVariable String id) {
        return strategyService.findById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('LIDER')")
    public Strategy update(@PathVariable String id, @Valid @RequestBody StrategyRequest request, Authentication authentication) {
        return strategyService.update(id, request, authentication.getName());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('LIDER')")
    public ResponseEntity<Void> delete(@PathVariable String id, Authentication authentication) {
        strategyService.delete(id, authentication.getName());
        return ResponseEntity.noContent().build();
    }
}

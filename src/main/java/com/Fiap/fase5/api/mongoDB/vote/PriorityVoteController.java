package com.Fiap.fase5.api.mongoDB.vote;

import com.Fiap.fase5.api.mongoDB.vote.dto.PriorityScoreResponse;
import com.Fiap.fase5.api.mongoDB.vote.dto.PriorityVoteRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ideas/{ideaId}/votes")
@PreAuthorize("hasRole('GESTOR')")
public class PriorityVoteController {

    private final PriorityVoteService priorityVoteService;

    public PriorityVoteController(PriorityVoteService priorityVoteService) {
        this.priorityVoteService = priorityVoteService;
    }

    @PostMapping
    public ResponseEntity<PriorityVote> vote(@PathVariable String ideaId,
                                             @Valid @RequestBody PriorityVoteRequest request,
                                             Authentication authentication) {
        PriorityVote vote = priorityVoteService.vote(ideaId, authentication.getName(), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(vote);
    }

    @GetMapping("/score")
    public PriorityScoreResponse score(@PathVariable String ideaId) {
        return priorityVoteService.calculateScoreTotal(ideaId);
    }
}

package com.Fiap.fase5.api.mongoDB.vote;

import com.Fiap.fase5.api.mongoDB.idea.Idea;
import com.Fiap.fase5.api.mongoDB.idea.IdeaRepository;
import com.Fiap.fase5.api.mongoDB.idea.IdeaService;
import com.Fiap.fase5.api.mongoDB.audit.AuditLogService;
import com.Fiap.fase5.api.mongoDB.vote.dto.PriorityScoreResponse;
import com.Fiap.fase5.api.mongoDB.vote.dto.PriorityVoteRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PriorityVoteService {

    private final PriorityVoteRepository priorityVoteRepository;
    private final IdeaRepository ideaRepository;
    private final IdeaService ideaService;
    private final AuditLogService auditLogService;

    public PriorityVoteService(PriorityVoteRepository priorityVoteRepository, IdeaRepository ideaRepository, IdeaService ideaService, AuditLogService auditLogService) {
        this.priorityVoteRepository = priorityVoteRepository;
        this.ideaRepository = ideaRepository;
        this.ideaService = ideaService;
        this.auditLogService = auditLogService;
    }

    public Idea vote(String ideaId, String voterEmail, PriorityVoteRequest request) {
        ensureIdeaExists(ideaId);

        if (priorityVoteRepository.existsByIdeaIdAndVoterEmail(ideaId, voterEmail)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Você já votou nesta ideia");
        }

        priorityVoteRepository.save(new PriorityVote(ideaId, voterEmail, request.score()));
        auditLogService.register("VOTE", "IDEA", ideaId, voterEmail);
        var votes = priorityVoteRepository.findByIdeaId(ideaId);
        int total = votes.stream().mapToInt(PriorityVote::getScore).sum();
        return ideaService.updateVoteSummary(ideaId, total, votes.size());
    }

    public PriorityScoreResponse calculateScoreTotal(String ideaId) {
        ensureIdeaExists(ideaId);
        int scoreTotal = priorityVoteRepository.findByIdeaId(ideaId).stream()
                .mapToInt(PriorityVote::getScore)
                .sum();
        return new PriorityScoreResponse(ideaId, scoreTotal);
    }

    private void ensureIdeaExists(String ideaId) {
        if (!ideaRepository.existsById(ideaId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ideia não encontrada");
        }
    }
}

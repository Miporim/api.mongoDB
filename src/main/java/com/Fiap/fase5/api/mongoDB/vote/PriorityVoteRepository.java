package com.Fiap.fase5.api.mongoDB.vote;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PriorityVoteRepository extends MongoRepository<PriorityVote, String> {

    boolean existsByIdeaIdAndVoterEmail(String ideaId, String voterEmail);

    List<PriorityVote> findByIdeaId(String ideaId);
}

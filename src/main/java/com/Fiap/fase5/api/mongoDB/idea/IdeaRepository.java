package com.Fiap.fase5.api.mongoDB.idea;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IdeaRepository extends MongoRepository<Idea, String> {

    List<Idea> findByCreatedByEmail(String createdByEmail);
}

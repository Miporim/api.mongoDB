package com.Fiap.fase5.api.mongoDB.project;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjectRepository extends MongoRepository<Project, String> {
    java.util.List<Project> findByUserCreator(String userCreator);
}

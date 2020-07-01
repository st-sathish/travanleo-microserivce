package com.travanleo.comment.commands.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandSourceRepository extends MongoRepository<CommandSource, String> {

}

package io.todos;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventsRepo extends MongoRepository<ConsoleLog, String> {

}

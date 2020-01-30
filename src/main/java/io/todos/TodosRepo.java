package io.todos;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TodosRepo extends MongoRepository<Todo, String> {
    Todo findByTitle(String title);
}

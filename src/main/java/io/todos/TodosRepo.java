package io.todos;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface TodosRepo extends PagingAndSortingRepository<Todo, String> {
    Todo findByTitle(String title);
}

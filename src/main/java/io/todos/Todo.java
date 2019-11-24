package io.todos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("todos")
class Todo {
    @Id
    private String id;
    private String title = "";
    private Boolean complete = Boolean.FALSE;
}

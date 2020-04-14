package io.todos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("todos")
public class Todo {
    @Id
    private String id;
    private String title = "";
    private Boolean complete = Boolean.FALSE;
    private List tags;
}

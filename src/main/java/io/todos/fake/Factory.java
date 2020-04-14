package io.todos.fake;

import io.todos.Todo;

public class Factory {

    public static Todo createFake() {
        return Todo.builder().build();
    }

    public static String pick(String[] values) {
        return values[(int)Math.floor(Math.random() * values.length)];
    }
}

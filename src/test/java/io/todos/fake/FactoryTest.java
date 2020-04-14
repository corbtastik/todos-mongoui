package io.todos.fake;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FactoryTest {

    @Test
    void contextLoads() {
        String modifier = Factory.pick(Dictionary.modifiers);
        String noun = Factory.pick(Dictionary.nouns);
        String verb = Factory.pick(Dictionary.verbs);
        System.out.println(modifier + " " + verb + " " + noun);
    }
}

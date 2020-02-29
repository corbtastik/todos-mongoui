package io.todos;

import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RestController
public class WebAPI {

    private static final Logger LOG = LoggerFactory.getLogger(WebAPI.class);

    private Environment env;

    private BuildProperties buildProperties;

    private final TodosRepo todosRepo;

    private final EventsRepo eventsRepo;

    @Autowired
    public WebAPI(Environment environment,
        BuildProperties buildProperties,
        TodosRepo todosRepo,
        EventsRepo eventsRepo) {
        this.env = environment;
        this.buildProperties = buildProperties;
        this.todosRepo = todosRepo;
        this.eventsRepo = eventsRepo;
    }

    @GetMapping("/metadata")
    public List<Metadata> metadata() {
        Map<String, Object> allProperties = new HashMap<>();
        for(PropertySource<?> ps : ((AbstractEnvironment)env).getPropertySources()) {
            if(ps instanceof MapPropertySource) {
                allProperties.putAll(((MapPropertySource)ps).getSource());
            }
        }
        return allProperties.entrySet().stream()
            .filter(it -> !StringUtils.isEmpty(it.getValue()))
            .sorted(Map.Entry.comparingByKey())
            .map(it -> Metadata.builder()
                    .property(it.getKey())
                    .value(it.getValue().toString()).build())
            .collect(Collectors.toList());
    }

    @GetMapping("/about")
    public BuildProperties buildInfo() {
        return buildProperties;
    }

    @GetMapping("/logs")
    public String logs() {
        return "Online";
    }

    @PostMapping("/logs")
    public void consoleLog(@RequestBody ConsoleLog consoleLog) {
        if("ERROR".equalsIgnoreCase(consoleLog.getLevel())) {
            LOG.error(consoleLog.getLine());
        } else if("WARN".equalsIgnoreCase(consoleLog.getLevel())) {
            LOG.warn(consoleLog.getLine());
        } else if("INFO".equalsIgnoreCase(consoleLog.getLevel())) {
            LOG.info(consoleLog.getLine());
        } else if("DEBUG".equalsIgnoreCase(consoleLog.getLevel())) {
            LOG.debug(consoleLog.getLine());
        } else if("TRACE".equalsIgnoreCase(consoleLog.getLevel())) {
            LOG.trace(consoleLog.getLine());
        }
        // lastly save to mongo yo
        eventsRepo.save(ConsoleLog.builder().level(
            consoleLog.getLevel()).line(consoleLog.getLine()).createdDate(LocalDateTime.now()).build());
    }

    @PostMapping("/todos")
    @ResponseStatus(HttpStatus.CREATED)
    public Todo create(@RequestBody Todo todo) {
        return todosRepo.save(todo);
    }

    @GetMapping("/todos")
    public Iterable<Todo> retrieve() {
        return todosRepo.findAll();
    }

    @GetMapping("/todos/{id}")
    public Todo retrieve(@PathVariable String id) {
        return todosRepo.findById(id).orElseThrow(() -> new RuntimeException("todo.id" + id + " not found"));
    }

    @PatchMapping("/todos/{id}")
    public Todo update(@PathVariable String id, @RequestBody Todo todo) {
        if(!id.equals(todo.getId())) {
            throw new RuntimeException("id != todo.id, inconsistent update.");
        }
        if(!todosRepo.existsById(id)) {
            throw new RuntimeException("id = " + id + " not found, create first.");
        }
        return todosRepo.save(todo);
    }

    @DeleteMapping("/todos")
    public void delete() {
        todosRepo.deleteAll();
    }

    @DeleteMapping("/todos/{id}")
    public void delete(@PathVariable String id) {
        todosRepo.deleteById(id);
    }

    @PostMapping("/faker/{size}")
    @ResponseStatus(HttpStatus.CREATED)
    public Integer faker(@PathVariable Integer size) {
        int i = 0;
        Faker faker = new Faker();
        for(; i < size; i++) {
            Todo todo = Todo.builder().title("Call " + faker.name().firstName() + " " + faker.name().lastName()).build();
            todosRepo.save(todo);
            LOG.debug("Fake Todo " + todo);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) { }
        }
        return i;
    }
}

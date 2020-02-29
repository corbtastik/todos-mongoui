package io.todos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@SpringBootApplication
@EnableMongoRepositories
public class WebUI implements CommandLineRunner {

    public static void main(String[] args) {
		SpringApplication.run(WebUI.class, args);
	}

	@Value("${todos.webui.placeholder:cf push something?}")
	private String placeholder;

    @Value("${spring.security.user.name}")
    private String username;

    @Autowired
    private TodosRepo todosRepo;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("placeholder", placeholder);
        model.addAttribute("username", username);
        return "index";
    }

    @Override
    public void run(String... args) {
        todosRepo.save(Todo.builder().title("Freedom to Run anywhere").build());
        todosRepo.save(Todo.builder().title("Put data where you want").build());
        todosRepo.save(Todo.builder().title("Best way to work with data").build());
    }
}
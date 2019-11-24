package io.todos;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@SpringBootApplication
public class WebUI {

    public static void main(String[] args) {
		SpringApplication.run(WebUI.class, args);
	}

	@Value("${todos.webui.placeholder:cf push something?}")
	private String placeholder;

    @Value("${spring.security.user.name}")
    private String username;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("placeholder", placeholder);
        model.addAttribute("username", username);
        return "index";
    }
}





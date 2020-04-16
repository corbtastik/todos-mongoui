package io.todos.fake;

import io.todos.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public final class Factory {

    private static final Logger LOG = LoggerFactory.getLogger(Factory.class);
    private static final Random rand = new Random();
    private static List<String> animals = loadThings("animals.csv");
    private static List<String> cars = loadThings("cars.csv");
    private static List<String> countries = loadThings("countries.csv");
    private static List<String> firstNames = loadThings("firstNames.csv");
    private static List<String> lastNames = loadThings("lastNames.csv");
    private static List<String> movies = loadThings("movies.csv");
    private static List<String> skills = loadThings("skills.csv");
    private static List<String> states = loadThings("states.csv");
    private static List<String> stocks = loadThings("stocks.csv");
    private static Map<String, List<String>> things = new HashMap<>();
    static {
        things.put("animals", animals);
        things.put("cars", cars);
        things.put("countries", countries);
        things.put("firstNames", firstNames);
        things.put("lastNames", lastNames);
        things.put("movies", movies);
        things.put("skills", skills);
        things.put("states", states);
        things.put("stocks", stocks);
    }

    public static Todo createFake() {

        Integer n = pick(0, things.keySet().size() - 1);
        switch (n) {
            case 0:
                return Todo.builder().title(animalTodo(pickAnimal())).build();
            case 1:
                return Todo.builder().title(carTodo(pickCar())).build();
            case 2:
                return Todo.builder().title(countryTodo(pickCountry())).build();
            case 3:
                return Todo.builder().title(firstNameTodo(pickFirstName())).build();
            case 4:
                return Todo.builder().title(lastNameTodo(pickLastName())).build();
            case 5:
                return Todo.builder().title(movieTodo(pickMovie())).build();
            case 6:
                return Todo.builder().title(skillTodo(pickSkill())).build();
            case 7:
                return Todo.builder().title(stateTodo(pickStates())).build();
            case 8:
                return Todo.builder().title(stockTodo(pickStocks())).build();
        }
        return Todo.builder().title("Get some milk").build();
    }

    private static Integer pick(Integer min, Integer max) {
        return rand.nextInt((max - min) + 1) + min;
    }

    private static List<String> loadThings(String things) {
        Resource resource = new ClassPathResource("/data/" + things);
        List<String> listOfThings = new ArrayList<>();
        try (InputStream input = resource.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            listOfThings = reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            LOG.error("Error Loading " + things, e);
        }

        return listOfThings;
    }

    private static String pickAnimal() {
        return animals.get(pick(0, animals.size() - 1));
    }

    private static String pickCar() {
        return cars.get(pick(0, cars.size() - 1));
    }

    private static String pickCountry() {
        return countries.get(pick(0, countries.size() - 1));
    }

    private static String pickFirstName() {
        return firstNames.get(pick(0, firstNames.size() - 1));
    }

    private static String pickLastName() {
        return lastNames.get(pick(0, lastNames.size() - 1));
    }

    private static String pickMovie() {
        return movies.get(pick(0, movies.size() - 1));
    }

    private static String pickSkill() {
        return skills.get(pick(0, skills.size() - 1));
    }

    private static String pickStates() {
        return states.get(pick(0, states.size() - 1));
    }

    private static String pickStocks() {
        return stocks.get(pick(0, stocks.size() - 1));
    }

    private static String animalTodo(String thing) {
        int n = rand.nextInt(10);
        switch (n) {
            case 0:
                return "Take my " + thing + " for a walk";
            case 1:
                return "Pet my " + thing;
            case 2:
                return "Feed my " + thing;
            case 3:
                return "Buy a " + thing;
            case 4:
                return "Take my " + thing + " to the vet";
            case 5:
                return "Sing to a " + thing;
            case 6:
                return "See a " + thing + " in the wild";
            case 7:
                return "Give a " + thing + " a hug";
            case 8:
                return "Read poetry to a " + thing;
            case 9:
                return "Sell my " + thing;
            case 10:
                return "Kiss a " + thing;
            default:
                return "Read to a " + thing;
        }
    }

    private static String carTodo(String thing) {
        int n = rand.nextInt(10);
        switch (n) {
            case 0:
                return "Wash my " + thing;
            case 1:
                return "Sell my " + thing;
            case 2:
                return "Buy a " + thing;
            case 3:
                return "Make payment on " + thing;
            case 4:
                return "Race my " + thing;
            case 5:
                return "Change oil on the " + thing;
            case 6:
                return "Take my " + thing + " to the shop";
            case 7:
                return "Gas up the " + thing;
            case 8:
                return "The " + thing + " needs an air-freshener";
            case 9:
                return "Add some rims to my " + thing;
            case 10:
                return "Wax the " + thing;
            default:
                return "Call insurance on the " + thing;
        }
    }

    private static String countryTodo(String thing) {
        int n = rand.nextInt(10);
        switch (n) {
            case 0:
                return "Take a trip to " + thing;
            case 1:
                return "Buy a house in " + thing;
            case 2:
                return "Find " + thing + " on a map";
            case 3:
                return "Sell the house in " + thing;
            case 4:
                return "Try food from " + thing;
            case 5:
                return "Draw the flag of " + thing;
            case 6:
                return "Run across " + thing;
            case 7:
                return "Find population of " + thing;
            case 8:
                return "What's the native language of " + thing;
            case 9:
                return "Make a friend in " + thing;
            case 10:
                return "Get passport for " + thing + " trip";
            default:
                return "Find Air BnB in " + thing;
        }
    }

    private static String firstNameTodo(String thing) {
        int n = rand.nextInt(10);
        switch (n) {
            case 0:
                return "Call " + thing + " back";
            case 1:
                return "Reply to email from " + thing;
            case 2:
                return "Take " + thing + " to dinner";
            case 3:
                return "Text " + thing + " about that thing";
            case 4:
                return "Send " + thing + " a card";
            case 5:
                return "Ask " + thing + " to the movies";
            case 6:
                return "Ride bikes with " + thing;
            case 7:
                return "Take piano lessons from " + thing;
            case 8:
                return "Connect with " + thing + " on LinkedIn";
            case 9:
                return "Follow " + thing + " on Twitter";
            case 10:
                return "Add " + thing + " as Facebook friend";
            default:
                return "Send " + thing + " a gift";
        }
    }

    private static String lastNameTodo(String thing) {
        int n = rand.nextInt(10);
        switch (n) {
            case 0:
                return "Add " + pickFirstName() + " " + thing + " to meeting";
            case 1:
                return "Setup email for " + pickFirstName() + " " + thing;
            case 2:
                return "Contact Lawyer " + pickFirstName() + " " + thing;
            case 3:
                return "Setup interview for " + pickFirstName() + " " + thing;
            case 4:
                return "Invite the " + thing + "s over for dinner";
            case 5:
                return "Call Dr. " + thing;
            case 6:
                return "Mow the " + thing + "'s yard";
            case 7:
                return "Feed the " + thing + "'s " + pickAnimal();
            case 8:
                return "Create account for " + pickFirstName() + " " + thing;
            case 9:
                return "Send the " + thing + "s a party invite";
            case 10:
                return "Go to the " + thing + "'s party";
            default:
                return "Vacay with the " + thing + "s";
        }
    }

    private static String movieTodo(String thing) {
        int n = rand.nextInt(10);
        switch (n) {
            case 0:
                return "Watch " + thing;
            case 1:
                return "Ask " + pickFirstName() + " to see " + thing;
            case 2:
                return "Record " + thing;
            case 3:
                return "Get popcorn for " + thing;
            case 4:
                return "Buy " + pick(2, 10) + " tickets for " + thing;
            case 5:
                return "Find the " + thing + " VHS tape";
            case 6:
                return "Watch " + thing + " in subtitle mode";
            case 7:
                return "Find Director of " + thing;
            case 8:
                return "What's the cast of " + thing;
            case 9:
                return "Review " + thing;
            case 10:
                return "Find Rotten Tomatoes on " + thing;
            default:
                return "Buy movie passes for " + thing;
        }
    }

    private static String skillTodo(String thing) {
        int n = rand.nextInt(10);
        switch (n) {
            case 0:
                return "Add " + thing + " to resume";
            case 1:
                return "Update LinkedIn with " + thing;
            case 2:
                return "Apply for " + thing + " job";
            case 3:
                return "Learn how to be a " + thing;
            case 4:
                return "Hire " + pickFirstName() + " for " + thing;
            case 5:
                return "Post job for " + thing;
            case 6:
                return "Get applicants for " + thing;
            case 7:
                return "Find someone for " + thing + " position";
            case 8:
                return "Remove " + thing + " posting";
            case 9:
                return "What does a " + thing + " do";
            case 10:
                return "Call " + pickFirstName() + " about " + thing;
            default:
                return "Add internal posting for " + thing;
        }
    }

    private static String stateTodo(String thing) {
        int n = rand.nextInt(10);
        switch (n) {
            case 0:
                return "Visit " + thing;
            case 1:
                return "Move to " + thing;
            case 2:
                return "Find state bird of " + thing;
            case 3:
                return "What's the capital of " + thing;
            case 4:
                return "Book hotel for " + thing + " trip";
            case 5:
                return "Sell house in " + thing;
            case 6:
                return "What's the population of " + thing;
            case 7:
                return "Buy land in " + thing;
            case 8:
                return "What's the state motto of " + thing;
            case 9:
                return "Draw the " + thing + " flag";
            case 10:
                return "Catch next flight to " + thing;
            default:
                return "Find state flower of " + thing;
        }
    }

    private static String stockTodo(String thing) {
        int n = rand.nextInt(10);
        switch (n) {
            case 0:
                return "Buy " + pick(10, 10000) + " shares of " + thing;
            case 1:
                return "Sell " + pick(10, 10000) + " shares of " + thing;
            case 2:
                return "Research trends of " + thing;
            case 3:
                return "Short sell " + thing;
            case 4:
                return "Find close price of " + thing;
            case 5:
                return "Get open price of " + thing;
            case 6:
                return "What's the daily high of " + thing;
            case 7:
                return "Find today's low of " + thing;
            case 8:
                return "Get 52W low of " + thing;
            case 9:
                return "Get 52W high of " + thing;
            case 10:
                return "Research Market Cap for " + thing;
            default:
                return "What's the 5Y yield on " + thing;
        }
    }
}

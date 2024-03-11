package com.mycompany.app;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;
import java.util.Arrays;

/**
 * The App class provides a method to count the number of true guesses in an
 * anagram game.
 * It compares two arrays of words and checks if each pair of words are anagrams
 * of each other.
 * The comparison can be case-sensitive or case-insensitive based on the
 * provided flag.
 */
public class App {

    public static int how_many_guess_is_true(String[] words1, String[] words2, int[] guess,
            boolean isAnagramCaseSensitive) {
        int count = 0;
        for (int i = 0; i < words1.length; i++) {
            for (int j = 0; j < words2.length; j++) {
                String first = words1[i];
                String second = words2[j];
                if (first.length() != second.length())
                    continue;
                if (!isAnagramCaseSensitive) {
                    first = first.toLowerCase();
                    second = second.toLowerCase();
                }
                char[] one = first.toCharArray();
                Arrays.sort(one);
                char[] two = second.toCharArray();
                Arrays.sort(two);
                if (Arrays.equals(one, two)) {
                    count++;
                }
            }
        }
        int number_of_true_guess = 0;
        for (int i = 0; i < guess.length; i++) {
            if (count == guess[i]) {
                number_of_true_guess++;
            }
        }

        return number_of_true_guess;

    }

    public static void main(String[] args) {
        port(getHerokuAssignedPort());

        get("/", (req, res) -> "Hello, World!");

        post("/compute", (req, res) -> {
            String words1Input = req.queryParams("words1Input");
            String[] words1 = words1Input.split("[;\\r\\n]+");
            for (int i = 0; i < words1.length; i++) {
                System.out.println("words1: " + words1[i]);
            }

            String words2Input = req.queryParams("words2Input");
            String[] words2 = words2Input.split("[;\\r\\n]+");
            for (int i = 0; i < words2.length; i++) {
                System.out.println("words2: " + words2[i]);
            }

            String guessesInput = req.queryParams("words2Input").replaceAll("\\s", "");
            String[] guessesString = guessesInput.split(",");
            int[] guesses = Arrays.stream(guessesString).mapToInt(Integer::parseInt).toArray();
            for (int i = 0; i < guesses.length; i++) {
                System.out.println("guesses: " + guesses[i]);
            }

            String isAnagramCaseSensitiveInput = req.queryParams("isAnagramCaseSensitiveInput");
            boolean isAnagramCaseSensitive = Boolean.parseBoolean(isAnagramCaseSensitiveInput);
            System.out.println("isAnagramCaseSensitive: " + isAnagramCaseSensitive);
            int result = how_many_guess_is_true(words1, words2, guesses, isAnagramCaseSensitive);

            Map<String, Object> model = new HashMap<>();
            model.put("result", result);
            return new ModelAndView(model, "compute.mustache");
        }, new MustacheTemplateEngine());

        get("/compute", (rq, rs) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("result", "not computed yet!");
            return new ModelAndView(model, "compute.mustache");
        }, new MustacheTemplateEngine());
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }
}
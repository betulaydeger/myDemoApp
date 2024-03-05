package com.mycompany.app;

import java.util.Arrays;

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
}
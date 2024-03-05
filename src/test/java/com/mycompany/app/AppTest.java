package com.mycompany.app;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    private App app = new App();

    @Test
    public void testAnagramsCaseSensitive() {
        String[] words1 = { "listen", "write", "GOOGLE" };
        String[] words2 = { "silent", "ETIRW", "Google" };
        int[] guess = { 3, 2, 1 };
        int result = app.how_many_guess_is_true(words1, words2, guess, true);
        assertEquals(1, result); // There is 1 set of anagrams if case sensitive
    }

    @Test
    public void testAnagramsNotCaseSensitive() {
        String[] words1 = { "listen", "write", "GOOGLE" };
        String[] words2 = { "silent", "ETIRW", "Google" };
        int[] guess = { 4, 3, 2, 1 };
        int result = app.how_many_guess_is_true(words1, words2, guess, false);
        assertEquals(1, result); // There are 4 sets of anagrams if not case sensitive
    }

    @Test
    public void testEveryoneFalseGuess() {
        String[] words1 = { "hello", "world" };
        String[] words2 = { "odlwr", "ellho" };
        int[] guess = { 0, 0, 0, 0 };
        int result = app.how_many_guess_is_true(words1, words2, guess, true);
        assertEquals(0, result); // No anagrams if case sensitive
    }

    @Test(expected = NullPointerException.class)
    public void testNullArrays() {
        String[] words1 = null;
        String[] words2 = null;
        int[] guess = { 2, 3, 4 };
        int result = app.how_many_guess_is_true(words1, words2, guess, false);
    }
}

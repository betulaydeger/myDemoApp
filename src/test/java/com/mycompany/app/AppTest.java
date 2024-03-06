package com.mycompany.app;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Unit test for App.how_many_guess_is_true.
 */
public class AppTest {

    /**
     * Test case for checking anagrams with case sensitivity.
     */
    @Test
    public void testAnagramsCaseSensitive() {
        String[] words1 = { "listen", "write", "GOOGLE" };
        String[] words2 = { "silent", "ETIRW", "Google" };
        int[] guess = { 3, 2, 1 };
        int result = App.how_many_guess_is_true(words1, words2, guess, true);
        assertEquals(1, result);
    }

    /**
     * Test case for checking anagrams without case sensitivity.
     */
    @Test
    public void testAnagramsNotCaseSensitive() {
        String[] words1 = { "listen", "write", "GOOGLE" };
        String[] words2 = { "silent", "ETIRW", "Google" };
        int[] guess = { 4, 3, 2, 1 };
        int result = App.how_many_guess_is_true(words1, words2, guess, false);
        assertEquals(1, result);
    }

    /**
     * Test case for checking all false guesses.
     */
    @Test
    public void testEveryoneFalseGuess() {
        String[] words1 = { "hello", "world" };
        String[] words2 = { "odlwr", "ellho" };
        int[] guess = { 0, 0, 0, 0 };
        int result = App.how_many_guess_is_true(words1, words2, guess, true);
        assertEquals(0, result);
    }

    /**
     * Test case for checking null arrays.
     * Expects a NullPointerException to be thrown.
     */
    @Test(expected = NullPointerException.class)
    public void testNullArrays() {
        String[] words1 = null;
        String[] words2 = null;
        int[] guess = { 2, 3, 4 };
        int result = App.how_many_guess_is_true(words1, words2, guess, false);
    }
}

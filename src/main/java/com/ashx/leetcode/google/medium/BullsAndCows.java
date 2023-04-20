package com.ashx.leetcode.google.medium;

/**
 * <a href="https://leetcode.com/problems/bulls-and-cows/">Leetcode</a>
 */
public class BullsAndCows {
    public static void main(String[] args) {
        String secret = "1807", guess = "7810";
        System.out.println(getHint(secret, guess));
    }

    public static String getHint(String secret, String guess) {
        int bulls = 0, cows = 0;
        int[] secretCows = new int[10];
        int[] guessCows = new int[10];

        for (int i = 0; i < secret.length(); i++) {
            int secretInt = Character.getNumericValue(secret.charAt(i));
            int guessInt = Character.getNumericValue(guess.charAt(i));
            if (secretInt == guessInt) {
                bulls++;
            } else {
                secretCows[secretInt]++;
                guessCows[guessInt]++;
            }
        }

        for (int i = 0; i < 10; i++) {
            cows += Math.min(secretCows[i], guessCows[i]);
        }

        return bulls + "A" + cows + "B";
    }
}

package com.ashx.leetcode.google.medium;

/**
 * <a href="https://leetcode.com/problems/longest-repeating-character-replacement/">Leetcode</a>
 */
public class LongestRepeatingCharacterReplacement {
    public static void main(String[] args) {
        LongestRepeatingCharacterReplacement r = new LongestRepeatingCharacterReplacement();
        System.out.println(r.characterReplacement("ABAB", 2)); // 4
        System.out.println(r.characterReplacement("AABABBA", 1)); // 4
    }

    public int characterReplacement(String s, int k) {
        char[] c = s.toCharArray();
        int[] seen = new int[26];
        int maxCount = 0;
        int max = Integer.MIN_VALUE;
        int n = s.length();
        int left = 0;
        int right = 0;

        while (right < n) {
            // Perform operation on right
            maxCount = Math.max(maxCount, ++seen[c[right] - 'A']);

            // If the window size minus the count of the most frequent character is greater than k
            // Reduce the window from the left
            // maxCount is the maximum of same character, the condition dicdated we can change anything to the required character
            // so the remaining characters == length-maxCount. Here "current_length" is right-left+1. hope it is clear now
            while (right - left + 1 - maxCount > k) {
                seen[c[left] - 'A']--;
                left++;
            }

            // Compute the max length
            max = Math.max(max, right - left + 1);
            // Increment right to calculate for the next value
            right++;
        }

        return max;
    }
}

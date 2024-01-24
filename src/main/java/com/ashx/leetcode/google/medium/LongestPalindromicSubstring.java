package com.ashx.leetcode.google.medium;

/**
 * <a href="https://leetcode.com/problems/longest-palindromic-substring/">Leetcode</a>
 */
public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad")); // bab / aba
        System.out.println(longestPalindrome("cbbd")); // bb
    }

    public static String longestPalindrome(String s) {
        if (s.length() <= 1) {
            return s;
        }

        int maxLen = 1;
        int start = 0;
        int end = 0;
        boolean[][] dp = new boolean[s.length()][s.length()];

        for (int col = 0; col < s.length(); ++col) {
            dp[col][col] = true;
            for (int row = 0; row < col; ++row) {
                if (s.charAt(row) == s.charAt(col) && (col - row <= 2 || dp[row + 1][col - 1])) {
                    dp[row][col] = true;
                    if (col - row + 1 > maxLen) {
                        maxLen = col - row + 1;
                        start = row;
                        end = col;
                    }
                }
            }
        }

        return s.substring(start, end + 1);
    }
}

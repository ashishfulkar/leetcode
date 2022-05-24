package com.ashx.leetcode.google.medium;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * https://leetcode.com/problems/number-of-matching-subsequences/
 */
public class NumberOfMatchingSubsequences {
    public static void main(String[] args) {
        System.out.println(numMatchingSubseq("abcde", new String[]{"a", "bb", "acd", "ace"}));
    }

    public static int numMatchingSubseq(String S, String[] words) {
        Map<Character, Deque<String>> map = new HashMap<>();
        for (char c = 'a'; c <= 'z'; c++) {
            map.putIfAbsent(c, new LinkedList<>());
        }
        for (String word : words) {
            map.get(word.charAt(0)).addLast(word);
        }

        int count = 0;
        for (char c : S.toCharArray()) {
            Deque<String> queue = map.get(c);
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.removeFirst();
                if (word.length() == 1) {
                    count++;
                } else {
                    map.get(word.charAt(1)).addLast(word.substring(1));
                }
            }
        }
        return count;
    }

    public static int numMatchingSubseqTimeConsuming(String s, String[] words) {
        int cnt = 0;
        for (String word : words) {
            int w = 0;
            for (int i = 0; i < s.length() && w < word.length(); i++) {
                if (s.charAt(i) == word.charAt(w)) {
                    w++;
                }
            }
            if (w == word.length()) {
                cnt++;
            }
        }

        return cnt;
    }
}

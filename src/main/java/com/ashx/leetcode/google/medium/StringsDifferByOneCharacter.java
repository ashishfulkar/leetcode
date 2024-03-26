package com.ashx.leetcode.google.medium;

import java.util.HashSet;

/**
 * <a href="https://leetcode.com/problems/strings-differ-by-one-character/">Leetcode</a>
 */
public class StringsDifferByOneCharacter {
    public static void main(String[] args) {
        StringsDifferByOneCharacter s = new StringsDifferByOneCharacter();
        // String[] dict = {"abcd", "acbd", "aacd"}; // true
        // String[] dict = {"ab", "cd", "yz"}; // false
        String[] dict = {"abcd", "cccc", "abyd", "abab"}; // true
        System.out.println(s.differByOneGood(dict));
    }

    public boolean differByOne(String[] dict) {
        HashSet<String> set = new HashSet<>();
        StringBuilder sb;
        for (String str : dict) {
            sb = new StringBuilder(str);
            for (int i = 0; i < str.length(); i++) {
                char actual = sb.charAt(i);
                sb.setCharAt(i, '*');
                String candidate = sb.toString();
                if (!set.add(candidate)) {
                    return true;
                }
                sb.setCharAt(i, actual);
            }
            sb.setLength(0);
        }
        return false;
    }

    public boolean differByOneGood(String[] dict) {
        HashSet<String> set = new HashSet<>();
        if (dict.length < 2) return false;
        set.add(dict[0]);
        for (int j = 1; j < dict.length; j++) {
            String s = dict[j];
            for (int i = 0; i < s.length(); i++) {
                for (int c = 97; c < 123; c++) {
                    char ch = (char) c;
                    if (set.contains(s.substring(0, i) + ch + s.substring(i + 1))) return true;
                }
            }
            set.add(s);
        }
        return false;
    }
}

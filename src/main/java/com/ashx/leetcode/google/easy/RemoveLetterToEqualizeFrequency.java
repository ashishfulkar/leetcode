package com.ashx.leetcode.google.easy;

import java.util.TreeMap;

/**
 * <a href="https://leetcode.com/problems/remove-letter-to-equalize-frequency/">Leetcode</a>
 */
public class RemoveLetterToEqualizeFrequency {
    public static void main(String[] args) {
        System.out.println(String.valueOf(equalFrequency("abcc"))); // true
        System.out.println(String.valueOf(equalFrequency("aazz"))); // false
        System.out.println(String.valueOf(equalFrequency("bac"))); // true
        System.out.println(String.valueOf(equalFrequency("aca"))); // true
    }

    private static boolean equalFrequency(String word) {
        int[] count = new int[26];
        for (char c : word.toCharArray()) {
            count[c - 'a']++;
        }
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : count) {
            if (num == 0) {
                continue;
            }
            int freq = map.getOrDefault(num, 0);
            map.put(num, freq + 1);
        }
        if (map.size() == 2 && map.firstKey() + 1 == map.lastKey() && map.get(map.lastKey()) == 1) {
            return true;
        }
        if (map.size() == 2 && map.firstKey() == 1 && map.get(map.firstKey()) == 1) {
            return true;
        }
        if (map.size() == 1) {
            return map.firstKey() == 1 || map.get(map.firstKey()) == 1;
        }
        return false;
    }
}

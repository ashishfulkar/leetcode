package com.ashx.leetcode.google.easy;

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {
    public static final Map<Character, Integer> romanMap = new HashMap<Character, Integer>() {{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
    }};

    public static void main(String[] args) {
        System.out.println(romanToInt("III"));
        System.out.println(romanToInt("LVIII"));
        System.out.println(romanToInt("LVIII"));
    }

    public static int romanToInt(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            boolean digAdded = false;
            if (i + 1 < s.length()) {
                char c2 = s.charAt(i + 1);
                if ((c1 == 'I' && (c2 == 'V' || c2 == 'X'))
                        || (c1 == 'X' && (c2 == 'L' || c2 == 'C'))
                        || (c1 == 'C' && (c2 == 'D' || c2 == 'M'))) {
                    res += romanMap.get(c2) - romanMap.get(c1);
                    i++;
                    digAdded = true;
                }
            }
            if (!digAdded) {
                res += romanMap.get(c1);
            }
        }
        return res;
    }
}

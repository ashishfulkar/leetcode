package com.ashx.leetcode.google.medium;

/**
 * <a href="https://leetcode.com/problems/shortest-way-to-form-string/description/">Leetcode</a>
 */
public class ShortestWayToFormString {
    public static void main(String[] args) {
        ShortestWayToFormString s = new ShortestWayToFormString();
        // System.out.println(s.shortestWay("abc", "abcbc")); // 2
        // System.out.println(s.shortestWay("abc", "acdbc")); // -1
        // System.out.println(s.shortestWay("xyz", "xzyxz")); // 3
        System.out.println(s.shortestWay("aaaaa", "aaaaaaaaaaaaa")); // 3
    }

    public int shortestWay(String source, String target) {
        int sLen = source.length();
        int i = 0;
        for (char t : target.toCharArray()) {
            int curr = i;
            while (true) {
                if (i - curr == sLen) {
                    return -1;
                }
                if (source.charAt(i % sLen) == t) {
                    i++;
                    break;
                }
                i++;
            }
        }
        return i / sLen + (i % sLen == 0 ? 0 : 1);
    }
}

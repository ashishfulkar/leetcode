package com.ashx.leetcode.google.medium;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * <a href="https://leetcode.com/problems/find-and-replace-in-string/">Leetcode</a>
 */
public class FindAndReplaceInString {
    public static void main(String[] args) {
        FindAndReplaceInString s = new FindAndReplaceInString();
        System.out.println(s.findReplaceString("vmokgggqzp", new int[]{3, 5, 1}, new String[]{"kg", "ggq", "mo"}, new String[]{"s", "so", "bfr"})); // vbfrssozp
        System.out.println(s.findReplaceString("abcd", new int[]{0, 2}, new String[]{"a", "cd"}, new String[]{"eee", "ffff"})); // eeebffff
        System.out.println(s.findReplaceString("abcd", new int[]{0, 2}, new String[]{"ab", "ec"}, new String[]{"eee", "ffff"})); // eeecd
    }

    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        List<int[]> sorted = new ArrayList<>();
        for (int i = 0; i < indices.length; i++) {
            sorted.add(new int[]{indices[i], i});
        }
        sorted.sort(Comparator.comparing(i -> -i[0]));
        for (int[] ind : sorted) {
            int srcIdx = ind[0], arrIdx = ind[1];
            String src = sources[arrIdx], target = targets[arrIdx];
            if (s.startsWith(src, srcIdx)) {
                s = s.substring(0, srcIdx) + target + s.substring(srcIdx + src.length());
            }
        }
        return s;
    }
}

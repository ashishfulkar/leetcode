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
        System.out.println(s.findReplaceString("vmokgggqzp", new int[]{3, 5, 1}, new String[]{"kg", "ggq", "mo"}, new String[]{"s", "so", "bfr"}));
    }

    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        List<int[]> sorted = new ArrayList<>();
        for (int i = 0; i < indices.length; i++) {
            sorted.add(new int[]{indices[i], i});
        }
        sorted.sort(Comparator.comparing(i -> -i[0]));
        for (int[] ind : sorted) {
            int i = ind[0], j = ind[1];
            String src = sources[j], target = targets[j];
            if (s.startsWith(src, i)) {
                s = s.substring(0, i) + target + s.substring(i + src.length());
            }
        }
        return s;
    }
}

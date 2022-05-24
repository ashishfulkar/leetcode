package com.ashx.leetcode.google.medium;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * https://leetcode.com/problems/find-and-replace-in-string/
 */
public class FindAndReplaceInString {
    public static void main(String[] args) {
        System.out.println(findReplaceString("vmokgggqzp", new int[]{3, 5, 1},
                new String[]{"kg", "ggq", "mo"}, new String[]{"s", "so", "bfr"}));
    }

    public static String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        List<int[]> sorted = new ArrayList<>();
        for (int i = 0; i < indexes.length; i++) {
            sorted.add(new int[]{indexes[i], i});
        }
        sorted.sort(Comparator.comparing(i -> -i[0]));
        for (int[] ind : sorted) {
            int i = ind[0], j = ind[1];
            String s = sources[j], t = targets[j];
            if (S.startsWith(s, i)) {
                S = S.substring(0, i) + t + S.substring(i + s.length());
            }
        }
        return S;
    }
}

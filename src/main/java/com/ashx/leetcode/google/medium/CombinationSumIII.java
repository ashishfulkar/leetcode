package com.ashx.leetcode.google.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * <a href="https://leetcode.com/problems/combination-sum-iii/">Leetcode</a>
 */
public class CombinationSumIII {
    public static void main(String[] args) {
        List<List<Integer>> r1 = combinationSum3(3, 7);
        print(r1);
        List<List<Integer>> r2 = combinationSum3(3, 9);
        print(r2);
        List<List<Integer>> r3 = combinationSum3(4, 1);
        print(r3);
    }

    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> r = new ArrayList<>();
        LinkedList<Integer> c = new LinkedList<>();
        backtrack(1, n, k, c, r);

        return r;
    }

    private static void backtrack(int start, int remain, int k, LinkedList<Integer> c, List<List<Integer>> r) {
        if (remain == 0 && c.size() == k) {
            // Note: it's important to make a deep copy here,
            // Otherwise the combination would be reverted in other branch of backtracking.
            r.add(new ArrayList<>(c));
            return;
        } else if (remain < 0 || c.size() == k) {
            // Exceed the scope, no need to explore further.
            return;
        }

        // Iterate through the reduced list of candidates.
        for (int i = start; i <= 9; ++i) {
            c.add(i);
            backtrack(i + 1, remain - i, k, c, r);
            c.removeLast();
        }
    }

    private static void print(List<List<Integer>> r) {
        System.out.println("Solution:");
        if (!r.isEmpty()) {
            for (List<Integer> l : r) {
                System.out.println(Arrays.toString(l.toArray()));
            }
        } else {
            System.out.println("[]");
        }
        System.out.println();
    }

}

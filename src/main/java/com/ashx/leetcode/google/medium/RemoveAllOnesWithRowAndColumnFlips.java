package com.ashx.leetcode.google.medium;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/remove-all-ones-with-row-and-column-flips/">Leetcode</a>
 */
public class RemoveAllOnesWithRowAndColumnFlips {
    public static void main(String[] args) {
        RemoveAllOnesWithRowAndColumnFlips f = new RemoveAllOnesWithRowAndColumnFlips();
        // @formatter:off
        /* int[][] grid = {
                {0,1,0},
                {1,0,1},
                {0,1,0}
        }; // true */
        int[][] grid = {
                {1,1,0},
                {0,0,0},
                {0,0,0}
        }; // true
        // @formatter:on
        System.out.println(f.removeOnes(grid));
    }

    public boolean removeOnes(int[][] grid) {
        for (int i = 1; i < grid.length; i++) {
            if (!Arrays.equals(grid[i], grid[i - 1]) && !isOpp(grid[i], grid[i - 1])) {
                return false;
            }
        }
        return true;
/*
Both rows have to follow the same patterns for us to be able to flip them into all 0s.
and by the same patterns, I mean for any two rows, they either have to be

Exactly the same
Exactly opposite
If there exists a pair of two rows that do not meet the requirements above, we can't flip the table into all 0s.
Reason being that we will want to perform column flip if there are two rows that don't meet the requirement above, but when we do col flip, all rows have 1 element flipped as well, so no matter what we do, it won't be doable.
*/
    }

    private boolean isOpp(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == b[i]) {
                return false;
            }
        }
        return true;
    }
}
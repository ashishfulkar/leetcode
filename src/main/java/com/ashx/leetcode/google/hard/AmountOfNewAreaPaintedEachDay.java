package com.ashx.leetcode.google.hard;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/amount-of-new-area-painted-each-day/">Leetcode</a>
 */
public class AmountOfNewAreaPaintedEachDay {
    public static void main(String[] args) {
        AmountOfNewAreaPaintedEachDay d = new AmountOfNewAreaPaintedEachDay();
        // int[][] paint = {{1, 4}, {4, 7}, {5, 8}}; // [3, 3, 1]
        // int[][] paint = {{1, 4}, {5, 8}, {4, 7}}; // [3, 3, 1]
        int[][] paint = {{1, 5}, {2, 4}}; // [4,0]
        System.out.println(Arrays.toString(d.amountPainted(paint)));
        System.out.println(Arrays.toString(d.amountPaintedPerf(paint)));
    }

    public int[] amountPainted(int[][] paint) {
        int MAX_LEN = (int) (5 * Math.pow(10, 4) + 1);
        int n = paint.length;
        int[] work = new int[n];
        boolean[] wall = new boolean[MAX_LEN];
        for (int i = 0; i < paint.length; i++) {
            int[] day = paint[i];
            int dayCnt = 0;
            for (int j = day[0]; j < day[1]; j++) {
                if (!wall[j]) {
                    wall[j] = true;
                    dayCnt++;
                }
            }
            work[i] = dayCnt;
        }
        return work;
    }

    public int[] amountPaintedPerf(int[][] paint) {
        int n = paint.length;
        int[] ans = new int[n];
        int max = paint[0][1];
        for (int[] p : paint) {
            max = Math.max(max, p[1]);
        }
        int[] line = new int[max + 1];
        for (int i = 0; i < n; i++) {
            int[] p = paint[i];
            int start = p[0];
            int end = p[1];
            int amount = 0;
            while (start < end) {
                int nextJump = Math.max(start + 1, line[start]);
                amount += line[start] == 0 ? 1 : 0;
                line[start] = Math.max(line[start], end);
                start = nextJump;
            }
            ans[i] = amount;
        }
        return ans;
    }
}

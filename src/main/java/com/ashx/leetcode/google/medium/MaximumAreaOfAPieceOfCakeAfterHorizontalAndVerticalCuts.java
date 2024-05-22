package com.ashx.leetcode.google.medium;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/maximum-area-of-a-piece-of-cake-after-horizontal-and-vertical-cuts/">Leetcode</a>
 */
public class MaximumAreaOfAPieceOfCakeAfterHorizontalAndVerticalCuts {
    public static void main(String[] args) {
        MaximumAreaOfAPieceOfCakeAfterHorizontalAndVerticalCuts c = new MaximumAreaOfAPieceOfCakeAfterHorizontalAndVerticalCuts();
        // System.out.println(c.maxArea(5, 4, new int[]{1, 2, 4}, new int[]{1, 3})); // 4
        // System.out.println(c.maxArea(5, 4, new int[]{3, 1}, new int[]{1})); // 6
        System.out.println(c.maxAreaPerf(1000000000, 1000000000, new int[]{2}, new int[]{2})); // 81
    }

    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        long area = 0;
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        int[] hs = new int[horizontalCuts.length + 1];
        hs[0] = horizontalCuts[0];
        for (int i = 1; i < horizontalCuts.length; i++) {
            hs[i] = horizontalCuts[i] - horizontalCuts[i - 1];
        }
        hs[horizontalCuts.length] = h - horizontalCuts[horizontalCuts.length - 1];

        int[] vs = new int[verticalCuts.length + 1];
        vs[0] = verticalCuts[0];
        for (int i = 1; i < verticalCuts.length; i++) {
            vs[i] = verticalCuts[i] - verticalCuts[i - 1];
        }
        vs[verticalCuts.length] = w - verticalCuts[verticalCuts.length - 1];

        for (int k : hs) {
            for (int v : vs) {
                area = Math.max(area, (long) k * v);
            }
        }

        return (int) (area % 1000000007);
    }

    public int maxAreaPerf(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        int maxH = getMax(horizontalCuts, h);
        int maxV = getMax(verticalCuts, w);
        long area = (long) maxH * maxV;
        return (int) (area % 1000000007);
    }

    private int getMax(int[] arr, int len) {
        Arrays.sort(arr);
        int n = arr.length;
        int max = Math.max(arr[0], len - arr[n - 1]);
        for (int i = 1; i < n; i++) {
            max = Math.max(max, arr[i] - arr[i - 1]);
        }
        return max;
    }
}

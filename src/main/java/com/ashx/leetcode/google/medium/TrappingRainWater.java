package com.ashx.leetcode.google.medium;

/**
 * <a href="https://www.geeksforgeeks.org/trapping-rain-water/">geeksforgeeks</a>
 */
public class TrappingRainWater {
    public static void main(String[] args) {
        TrappingRainWater t = new TrappingRainWater();
        System.out.println(t.findWater(new int[]{2, 0, 2})); // 2
        System.out.println(t.findWater(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1})); // 6
        System.out.println(t.findWater(new int[]{3, 0, 2, 0, 4})); // 7
    }

    public int findWater(int[] arr) {
        int n = arr.length;
        int[] left = new int[n];
        int[] right = new int[n];
        int water = 0;

        // Fill left array
        left[0] = arr[0];
        for (int i = 1; i < n; i++)
            left[i] = Math.max(left[i - 1], arr[i]);

        // Fill right array
        right[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--)
            right[i] = Math.max(right[i + 1], arr[i]);

        // Calculate the accumulated water element by
        // element consider the amount of water on i'th bar,
        // the amount of water accumulated on this
        // particular bar will be equal to min(left[i],
        // right[i]) - arr[i] .
        for (int i = 0; i < n; i++)
            water += Math.min(left[i], right[i]) - arr[i];

        return water;
    }
}

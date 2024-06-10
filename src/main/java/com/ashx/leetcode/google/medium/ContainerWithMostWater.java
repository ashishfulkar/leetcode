package com.ashx.leetcode.google.medium;

/**
 * <a href="https://leetcode.com/problems/container-with-most-water/">Leetcode</a>
 */
public class ContainerWithMostWater {
    public static void main(String[] args) {
        ContainerWithMostWater w = new ContainerWithMostWater();
        System.out.println(w.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7})); // 49
        System.out.println(w.maxArea(new int[]{1, 1})); // 1
    }

    public int maxArea(int[] height) {
        int max = 0;
        int n = height.length;
        int start = 0, end = n - 1;
        while (start < end) {
            int min = Math.min(height[start], height[end]);
            max = Math.max(max, min * (end - start));
            while (height[start] <= min && start < end) {
                start++;
            }
            while (height[end] <= min && start < end) {
                end--;
            }
        }

        return max;
    }
}

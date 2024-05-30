package com.ashx.leetcode.google.medium;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/3sum-closest/">Leetcode</a>
 */
public class SumClosest3 {
    public static void main(String[] args) {
        SumClosest3 s = new SumClosest3();
        // System.out.println(s.threeSumClosest(new int[]{-1, 2, 1, -4}, 1)); // 2
        // System.out.println(s.threeSumClosest(new int[]{0, 0, 0}, 1)); // 0
        System.out.println(s.threeSumClosestPerf(new int[]{4, 0, 5, -5, 3, 3, 0, -4, -5}, -2)); // -2
    }

    public int threeSumClosest(int[] nums, int target) {
        if (nums.length <= 3) {
            return Arrays.stream(nums).sum();
        }
        Arrays.sort(nums);
        int closest = nums[0] + nums[1] + nums[2];

        for (int i = 1; i < nums.length - 2; i++) {
            int sum = nums[i] + nums[i + 1] + nums[i + 2];
            if (Math.abs(sum - target) < Math.abs(closest - target)) {
                closest = sum;
            }
        }

        return closest;
    }

    public int threeSumClosestPerf(int[] nums, int target) {
        int len = nums.length;
        int closest = 0;
        int max = Integer.MAX_VALUE;
        Arrays.sort(nums);

        for (int current = 0; current < len - 2; current++) {
            int left = current + 1;
            int right = len - 1;

            while (left < right) {
                int sum = nums[current] + nums[left] + nums[right];

                if (sum == target) {
                    return sum;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }

                int diff = Math.abs(sum - target);
                if (diff < max) {
                    max = diff;
                    closest = sum;
                }
            }
        }

        return closest;
    }
}

package com.ashx.leetcode.google.medium;

/**
 * <a href="https://leetcode.com/problems/jump-game/">Leetcode</a>
 */
public class JumpGame {
    public static void main(String[] args) {
        JumpGame g = new JumpGame();
        // int[] nums = {2, 3, 1, 1, 4}; // true
        int[] nums = {3, 2, 1, 0, 4}; // false
        System.out.println(g.canJump(nums));
    }

    public boolean canJump(int[] nums) {
        int minIdx = nums.length - 1;

        for (int i = minIdx - 1; i >= 0; i--) {
            if (i + nums[i] >= minIdx) {
                minIdx = i;
            }
        }

        return minIdx == 0;
    }
}

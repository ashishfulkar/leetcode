package com.ashx.leetcode.google.medium;

/**
 * <a href="https://leetcode.com/problems/jump-game-ii/">Leetcode</a>
 */
public class JumpGameII {
    public static void main(String[] args) {
        JumpGameII g = new JumpGameII();
        // int[] nums = {2, 3, 1, 1, 4}; // 2
        // int[] nums = {2, 3, 0, 1, 4}; // 2
        // int[] nums = {2, 0, 2, 4, 6, 0, 0, 3}; // 3
        int[] nums = {5, 9, 3, 2, 1, 0, 2, 3, 3, 1, 0, 0}; // 3
        System.out.println(g.jumpPerf(nums));
    }

    public int jump(int[] nums) {
        int n = nums.length;
        int[] jumps = new int[n];

        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] == 0) {
                continue;
            }
            int reach = i + nums[i];
            if (reach >= n - 1) {
                jumps[i] = 1;
                continue;
            }
            int min = Integer.MAX_VALUE;
            while (reach > i) {
                if (jumps[reach] > 0) {
                    min = Math.min(min, jumps[reach]);
                }
                reach--;
            }
            if (min != Integer.MAX_VALUE) {
                jumps[i] = min + 1;
            }
        }

        return jumps[0];
    }

    public int jumpPerf(int[] nums) {
        return nums.length == 1 ? 0 : jumpsToTarget(nums, 0);
    }

    public int jumpsToTarget(int[] nums, int start) {
        int possibleJumps = nums[start];
        int target = nums.length - 1;
        int maxJumps = 0;
        int nextStart = 0;

        if (start == target) return 0;

        if (start + possibleJumps >= target) return 1;

        for (int i = 1; i <= possibleJumps; i++) {
            if (i + nums[start + i] > maxJumps) {
                maxJumps = i + nums[start + i];
                nextStart = start + i;
            }
        }

        return 1 + jumpsToTarget(nums, nextStart);
    }
}

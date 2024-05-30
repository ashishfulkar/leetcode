package com.ashx.leetcode.google.medium;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/next-permutation/">Leetcode</a>
 */
public class NextPermutation {
    public static void main(String[] args) {
        NextPermutation p = new NextPermutation();
        int[] nums;

        nums = new int[]{1, 2, 3};
        System.out.print(Arrays.toString(nums));
        p.nextPermutation(nums);
        System.out.println("->" + Arrays.toString(nums));

        nums = new int[]{3, 2, 1};
        System.out.print(Arrays.toString(nums));
        p.nextPermutation(nums);
        System.out.println("->" + Arrays.toString(nums));

        nums = new int[]{1, 1, 5};
        System.out.print(Arrays.toString(nums));
        p.nextPermutation(nums);
        System.out.println("->" + Arrays.toString(nums));
    }

    public void nextPermutation(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return;
        }
        int i;
        for (i = len - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                break;
            }
        }
        if (i < 0) { // nums is in descending order
            reverse(nums, 0);
        } else {
            // nums[i + 1] -> nums[len - 1] is in descending order
            reverse(nums, i + 1);

            for (int j = i + 1; j < len; j++) {
                if (nums[j] > nums[i]) {
                    swap(nums, i, j);
                    break;
                }
            }
        }
    }

    private void reverse(int[] nums, int left) {
        int right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    private void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}

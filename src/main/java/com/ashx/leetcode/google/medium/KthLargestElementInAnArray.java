package com.ashx.leetcode.google.medium;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * <a href="https://leetcode.com/problems/kth-largest-element-in-an-array/">Leetcode</a>
 */
public class KthLargestElementInAnArray {
    public static void main(String[] args) {
        KthLargestElementInAnArray a = new KthLargestElementInAnArray();
        System.out.println(a.findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2)); // 5
        System.out.println(a.findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4)); // 4
    }

    public int findKthLargest1(int[] nums, int k) { // O(N*log(N))
        int n = nums.length;
        Arrays.sort(nums);
        return nums[n - k];
    }

    public int findKthLargest(int[] nums, int k) { // O()
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            minHeap.offer(nums[i]);
        }

        for (int i = k; i < nums.length; i++) {
            if (nums[i] > minHeap.peek()) {
                minHeap.poll();
                minHeap.offer(nums[i]);
            }
        }

        return minHeap.peek();
    }

}

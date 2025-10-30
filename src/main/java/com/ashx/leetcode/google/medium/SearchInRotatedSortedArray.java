package com.ashx.leetcode.google.medium;

/**
 * <a href="https://leetcode.com/problems/search-in-rotated-sorted-array/">Leetcode</a>
 */
public class SearchInRotatedSortedArray {
    public static void main(String[] args) {
        SearchInRotatedSortedArray a = new SearchInRotatedSortedArray();
        System.out.println(a.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0)); // 4
        System.out.println(a.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3)); // -1
        System.out.println(a.search(new int[]{1}, 0)); // -1
    }

    public int search1(int[] nums, int target) { //O(N)
        for (int i = 0; i < nums.length; i++) {
            if (target == nums[i]) {
                return i;
            }
        }
        return -1;
    }

    public int search(int[] nums, int target) { //O(log(N))
        int len = nums.length;
        if (len == 0) {
            return -1;
        }
        int pivot = getPivot(nums);
        if (nums[0] < target) {
            return search(nums, target, 0, pivot - 1);
        } else {
            return search(nums, target, pivot, len - 1);
        }
    }

    private int getPivot(int[] nums) {
        int s = 0, e = nums.length - 1;
        while (s < e) {
            int mid = s + (e - s) / 2;
            if (nums[mid] > nums[e]) {
                s = mid + 1;
            } else {
                e = mid;
            }
        }
        return s;
    }

    private int search(int[] nums, int target, int start, int end) {
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (target < nums[mid]) {
                end = mid - 1;
            } else if (target > nums[mid]) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

}

package com.ashx.leetcode.google.medium;

import java.util.Arrays;

public class HIndex {
    public static void main(String[] args) {
        System.out.println(hIndex(new int[]{3, 0, 6, 1, 5}));
        System.out.println(hIndex(new int[]{1, 3, 1}));
        System.out.println(hIndex(new int[]{100}));
        System.out.println(hIndex(new int[]{11, 15}));
    }

    public static int hIndex(int[] citations) {
        // sorting the citations in ascending order
        Arrays.sort(citations);
        // finding h-index by linear search
        int n = citations.length;
        int i = 0;
        while (i < n && citations[n - 1 - i] > i) {
            i++;
        }
        return i; // after the while loop, i = i' + 1
    }
}

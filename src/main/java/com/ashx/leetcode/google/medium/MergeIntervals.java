package com.ashx.leetcode.google.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * <a href="https://leetcode.com/problems/merge-intervals/">Leetcode</a>
 */
public class MergeIntervals {
    public static void main(String[] args) {
        MergeIntervals m = new MergeIntervals();
        // System.out.println(Arrays.deepToString(m.merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}}))); // [[1,6],[8,10],[15,18]]
        System.out.println(Arrays.deepToString(m.merge(new int[][]{{2, 3}, {2, 2}, {3, 3}, {1, 3}, {5, 7}, {2, 2}, {4, 6}}))); // [[1,3],[4,7]]
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);

        for (int[] interval : intervals) {
            if (q.isEmpty()) {
                q.add(interval);
            } else {
                int[] top = q.poll();
                if (top[1] < interval[0]) {
                    q.add(top);
                    q.add(interval);
                } else {
                    top[1] = Math.max(top[1], interval[1]);
                    q.add(top);
                }
            }
        }

        return q.toArray(new int[][]{});
    }
}

package com.ashx.leetcode.google.medium;

import java.util.HashMap;

/**
 * <a href="https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/">Leetcode</a>
 */
public class PairsOfSongsWithTotalDurationsDivisibleBy60 {
    public static void main(String[] args) {
        PairsOfSongsWithTotalDurationsDivisibleBy60 p = new PairsOfSongsWithTotalDurationsDivisibleBy60();
        System.out.println(p.numPairsDivisibleBy60Perf(new int[]{30, 20, 150, 100, 40})); // 3
        System.out.println(p.numPairsDivisibleBy60Perf(new int[]{60, 60, 60})); // 3
        System.out.println(p.numPairsDivisibleBy60Perf(new int[]{418, 204, 77, 278, 239, 457, 284, 263, 372, 279, 476, 416, 360, 18})); // 1
    }

    public int numPairsDivisibleBy60(int[] time) {
        int pairs = 0;
        int n = time.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((time[i] + time[j]) % 60 == 0) {
                    pairs++;
                }
            }
        }

        return pairs;
    }

    public int numPairsDivisibleBy60Perf2(int[] time) {
        int count = 0;
        for (int i = 0; i < time.length; i++) {
            if (time[i] >= 60) time[i] %= 60;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < time.length; i++) {
            if (time[i] == 0 && map.containsKey(time[i])) {
                count += map.get(time[i]);
            }
            if (map.containsKey(60 - time[i])) {
                count += map.get(60 - time[i]);
            }
            map.put(time[i], map.getOrDefault(time[i], 0) + 1);
        }
        return count;
    }

    public int numPairsDivisibleBy60Perf(int[] time) {
        int[] arr = new int[60];
        for (int t : time) {
            arr[t % 60]++;
        }
        int count = 0;
        count += (arr[0] * (arr[0] - 1)) / 2;
        count += (arr[30] * (arr[30] - 1)) / 2;
        for (int i = 1; i < 30; i++) {
            if (arr[i] > 0 && arr[60 - i] > 0) {
                count += (arr[i] * arr[60 - i]);

            }
        }
        return count;
    }
}

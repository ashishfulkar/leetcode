package com.ashx.leetcode.google.medium;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <a href="https://leetcode.com/problems/partition-labels/">Leetcode</a>
 */
public class PartitionLabels {
    public static void main(String[] args) {
        PartitionLabels l = new PartitionLabels();
        System.out.println(l.partitionLabels("ababcbacadefegdehijhklij")); // [9,7,8]
        System.out.println(l.partitionLabels("eccbbbbdec")); // [10]
    }

    public List<Integer> partitionLabels(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        if (len == 1) {
            return List.of(1);
        }
        List<Integer> partitions = new ArrayList<>();
        int[] arr = new int[123];
        for (char c : chars) {
            arr[c]++;
        }
        int left = 0, right = 0, prevLeft = 0;
        while (right < len) {
            int cnt = arr[chars[left]];
            while (cnt > 0 && right < len) {
                arr[chars[right]]--;
                cnt--;
                right++;
            }
            while (left < right) {
                if (arr[chars[left]] != 0) {
                    break;
                }
                left++;
            }
            if (left == right) {
                partitions.add(left - prevLeft);
                prevLeft = left;
            }
        }

        return partitions;
    }

    public List<Integer> partitionLabelsPerf(String s) {
        return new AbstractList<>() {
            LinkedList<Integer> result;

            private void init() {
                result = dfs(s.toCharArray(), 0, s.length() - 1, new int[26]);
            }

            private LinkedList<Integer> dfs(char[] words, int left, int right, int[] dp) {
                LinkedList<Integer> list = new LinkedList<>();

                if (left > right) {
                    return list;
                }

                for (int i = right; i >= left; --i) {
                    if (0 == dp[words[i] - 'a']) {
                        dp[words[i] - 'a'] = i + 1;
                    }
                }

                while (words[right] != words[left]) {
                    right--;
                }

                for (int i = left + 1; i < right; ++i) {
                    int newRight = dp[words[i] - 'a'] - 1;
                    if (newRight > right) {
                        right = newRight;
                    }
                }

                list.add(right - left + 1);
                list.addAll(dfs(words, right + 1, words.length - 1, dp));
                return list;
            }

            private void ENTRY_NO_ENV() {
                if (null == result) {
                    init();
                    System.gc();
                }
            }

            @Override
            public Integer get(int index) {
                ENTRY_NO_ENV();
                return result.get(index);
            }

            @Override
            public int size() {
                ENTRY_NO_ENV();
                return result.size();
            }
        };
    }
}

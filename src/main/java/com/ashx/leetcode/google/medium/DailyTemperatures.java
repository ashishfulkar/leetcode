package com.ashx.leetcode.google.medium;

import java.util.Arrays;
import java.util.Stack;

/**
 * <a href="https://leetcode.com/problems/daily-temperatures/">Leetcode</a>
 */
public class DailyTemperatures {
    public static void main(String[] args) {
        DailyTemperatures t = new DailyTemperatures();
        System.out.println(Arrays.toString(t.dailyTemperaturesPerf(new int[]{73, 74, 75, 71, 69, 72, 76, 73}))); // [1,1,4,2,1,1,0,0]
        System.out.println(Arrays.toString(t.dailyTemperaturesPerf(new int[]{30, 40, 50, 60}))); // [1,1,1,0]
        System.out.println(Arrays.toString(t.dailyTemperatures(new int[]{30, 60, 90}))); // [1,1,0]
    }

    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (temperatures[i] < temperatures[j]) {
                    result[i] = j - i;
                    break;
                }
            }
        }

        return result;
    }

    public int[] dailyTemperaturesPerf(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[temperatures.length];

        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int idx = stack.pop();
                result[idx] = i - idx;
            }
            stack.push(i);
        }

        return result;
    }
}

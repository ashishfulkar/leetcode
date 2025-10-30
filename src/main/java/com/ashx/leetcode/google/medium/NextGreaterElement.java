package com.ashx.leetcode.google.medium;

import java.util.Arrays;

/**
 * <a href="https://workat.tech/problem-solving/practice/next-greater-element">workat.tech</a>
 */
public class NextGreaterElement {
    public static void main(String[] args) {
        NextGreaterElement e = new NextGreaterElement();
        System.out.println(Arrays.toString(e.getNextGreaterElement(new int[]{3, 2, 1, 4, 5}))); // 4 4 4 5 -1
        System.out.println(Arrays.toString(e.getNextGreaterElement(new int[]{1, 5, 2, 3, 5}))); // 5 -1 3 5 -1
        System.out.println(Arrays.toString(e.getNextGreaterElement(new int[]{1, 2, 3, 4}))); // 2 3 4 -1
        System.out.println(Arrays.toString(e.getNextGreaterElement(new int[]{4, 3, 2, 1}))); // -1 -1 -1 -1
    }

    int[] getNextGreaterElement(int[] A) {
        int n = A.length;
        int[] ret = new int[n];
        ret[n - 1] = A[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            if (A[i] < A[i + 1]) {
                ret[i] = A[i + 1];
            } else if (A[i] == A[i + 1]) {
                ret[i] = -1;
            } else if (A[i] < ret[i + 1]) {
                ret[i] = ret[i + 1];
            } else {
                ret[i] = -1;
            }
        }
        ret[n - 1] = -1;

        return ret;
    }
}

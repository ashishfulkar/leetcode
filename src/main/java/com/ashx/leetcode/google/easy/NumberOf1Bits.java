package com.ashx.leetcode.google.easy;

/**
 * <a href="https://leetcode.com/problems/number-of-1-bits/">Leetcode</a>
 */
public class NumberOf1Bits {
    public static void main(String[] args) {
        NumberOf1Bits b = new NumberOf1Bits();
        System.out.println(b.hammingWeight(11)); // 3
        System.out.println(b.hammingWeight(128)); // 1
        System.out.println(b.hammingWeight(2147483645)); // 30
    }

    public int hammingWeight(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            if (((n >> i) & 1) == 1) {
                res += 1;
            }
        }
        return res;
    }
}

package com.ashx.leetcode.google.easy;

/**
 * <a href="https://leetcode.com/problems/reverse-bits/">Leetcode</a>
 */
public class ReverseBits {
    public static void main(String[] args) {
        ReverseBits b = new ReverseBits();
        System.out.println(b.reverseBits(43261596)); // 964176192
        System.out.println(b.reverseBits(2147483644)); // 1073741822
    }

    public int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result <<= 1;
            result |= (n & 1);
            n >>= 1;
        }
        return result;
    }
}

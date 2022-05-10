package com.ashx.leetcode.google.easy;

public class PalindromeNumber {
    public static void main(String[] args) {
        System.out.println(isPalindrome(1000030001));
//        System.out.println(isPalindrome(-121));
//        System.out.println(isPalindrome(10));
//        System.out.println(isPalindrome(11));
//        System.out.println(isPalindrome(1001));
//        System.out.println(isPalindrome(9999));
    }

    public static boolean isPalindrome(int x) { // 123454321
        if (x < 0) {
            return false;
        }
        int pow = 1;
        int length = (int) (Math.log10(x) + 1);
        int rightDig = x % (int) Math.pow(10, pow);
        int leftDig = x / (int) Math.pow(10, length - pow);
        if (leftDig != rightDig) {
            return false;
        }

        pow++;
        while (pow <= length / 2) {
            rightDig = (x % (int) Math.pow(10, pow)) / (int) Math.pow(10, pow - 1);
            leftDig = (x / (int) Math.pow(10, length - pow)) % 10;
            if (leftDig != rightDig) {
                return false;
            }
            pow++;
        }
        return true;
    }
}

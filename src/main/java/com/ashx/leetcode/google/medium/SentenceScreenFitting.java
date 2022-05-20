package com.ashx.leetcode.google.medium;

public class SentenceScreenFitting {
    public static void main(String[] args) {
//        System.out.println(wordsTyping(new String[]{"hello", "world"}, 2, 8));
//        System.out.println(wordsTyping(new String[]{"a", "bcd", "e"}, 3, 6));
//        System.out.println(wordsTyping(new String[]{"i","had","apple","pie"}, 4, 5));
        System.out.println(wordsTyping(new String[]{"a", "bcd"}, 20000, 20000));
    }

    public static int wordsTypingWorst(String[] sentence, int rows, int cols) {
        int res = 0;

        int n = sentence.length;
        int row = 1;
        int cnt = 0;
        while (row <= rows) {
            int colRem = cols;
            while (colRem > 0) {
                if (sentence[cnt % n].length() <= colRem) {
                    colRem -= sentence[cnt % n].length() + 1;
                    cnt++;
                    if (cnt % n == 0) {
                        res++;
                    }
                } else {
                    break;
                }
            }
            row++;
        }

        return res;
    }

    public static int wordsTyping(String[] sentence, int rows, int cols) {
        String s = String.join(" ", sentence) + " ";
        int start = 0, l = s.length();
        for (int i = 0; i < rows; i++) {
            start += cols;
            if (s.charAt(start % l) == ' ') {
                start++;
            } else {
                while (start > 0 && s.charAt((start-1) % l) != ' ') {
                    start--;
                }
            }
        }

        return start / s.length();
    }
}

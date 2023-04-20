package com.ashx.leetcode.google.medium;

import java.util.ArrayDeque;

/**
 * <a href="https://leetcode.com/problems/evaluate-reverse-polish-notation/">Leetcode</a>
 */
public class EvaluateReversePolishNotation {
    public static void main(String[] args) {
        String[] tokens = new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        System.out.println(evalRPN(tokens));
    }

    public static int evalRPN(String[] tokens) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (String token : tokens) {
            if ("+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token)) {
                int i1 = q.poll();
                int i2 = q.poll();
                switch (token) {
                    case "+":
                        q.push(i2 + i1);
                        break;
                    case "-":
                        q.push(i2 - i1);
                        break;
                    case "*":
                        q.push(i2 * i1);
                        break;
                    case "/":
                        q.push(i2 / i1);
                        break;
                }
            } else {
                q.push(Integer.parseInt(token));
            }
        }
        return q.poll();
    }
}

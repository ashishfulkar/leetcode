package com.ashx.leetcode.google.medium;

import java.util.Stack;

/**
 * <a href="https://leetcode.com/problems/evaluate-reverse-polish-notation/">Leetcode</a>
 */
public class EvaluateReversePolishNotation {
    public static void main(String[] args) {
        String[] tokens = new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}; // 22
        System.out.println(evalRPN(tokens));
    }

    public static int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            if ("+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token)) {
                int i1 = stack.pop();
                int i2 = stack.pop();
                switch (token) {
                    case "+":
                        stack.push(i2 + i1);
                        break;
                    case "-":
                        stack.push(i2 - i1);
                        break;
                    case "*":
                        stack.push(i2 * i1);
                        break;
                    case "/":
                        stack.push(i2 / i1);
                        break;
                }
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }
}

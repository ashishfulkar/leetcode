package com.ashx.leetcode.google.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class DecodeString {
    public static void main(String[] args) {
        System.out.println(decodeString("abc3[cd]xyz"));
    }

    public static String myFailedSolution(String s) {
        StringBuilder res = new StringBuilder();
        StringBuilder stackTmpBuff = new StringBuilder();
        StringBuilder tmpBuff = new StringBuilder();
        Stack<Pair> stack = new Stack<>();
        int rep = 0;
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                if (rep != 0) {
                    stack.add(Pair.of(tmpBuff.toString(), rep));
                    tmpBuff = new StringBuilder();
                    rep = 0;
                } else {
                    try {
                        Integer.parseInt(tmpBuff.toString());
                    } catch (NumberFormatException e) {
                        res.append(tmpBuff);
                        tmpBuff = new StringBuilder();
                    }
                }
                tmpBuff.append(c);
            } else if ('[' == c) {
                rep = Integer.parseInt(tmpBuff.toString());
                tmpBuff = new StringBuilder();
            } else if (']' == c) {
                if (rep != 0) {
                    stack.push(Pair.of(tmpBuff.toString(), rep));
                    tmpBuff = new StringBuilder();
                    rep = 0;
                }
                Pair pair = stack.pop();
                for (int i = 0; i < Objects.requireNonNull(pair).rep; i++) {
                    tmpBuff.append(pair.str).append(stackTmpBuff);
                }
                stackTmpBuff = tmpBuff;
                if (stack.size() == 0) {
                    res.append(stackTmpBuff);
                    stackTmpBuff = new StringBuilder();
                }
                tmpBuff = new StringBuilder();
            } else {
                tmpBuff.append(c);
            }
        }
        res.append(tmpBuff);

        return res.toString();
    }

    static class Pair {
        String str;
        int rep;

        public static Pair of(String str, int rep) {
            Pair pair = new Pair();
            pair.str = str;
            pair.rep = rep;
            return pair;
        }
    }


    public static String decodeString(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ']') {
                List<Character> decodedString = new ArrayList<>();
                // get the encoded string
                while (stack.peek() != '[') {
                    decodedString.add(stack.pop());
                }
                // pop [ from the stack
                stack.pop();
                int base = 1;
                int k = 0;
                // get the number k
                while (!stack.isEmpty() && Character.isDigit(stack.peek())) {
                    k = k + (stack.pop() - '0') * base;
                    base *= 10;
                }
                // decode k[decodedString], by pushing decodedString k times into stack
                while (k != 0) {
                    for (int j = decodedString.size() - 1; j >= 0; j--) {
                        stack.push(decodedString.get(j));
                    }
                    k--;
                }
            }
            // push the current character to stack
            else {
                stack.push(s.charAt(i));
            }
        }
        // get the result from stack
        char[] result = new char[stack.size()];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }
        return new String(result);
    }
}

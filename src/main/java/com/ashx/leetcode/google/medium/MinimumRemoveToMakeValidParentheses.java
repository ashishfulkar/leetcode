package com.ashx.leetcode.google.medium;

import java.util.LinkedHashMap;
import java.util.Stack;

/**
 * <a href="https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/">Leetcode</a>
 */
public class MinimumRemoveToMakeValidParentheses {
    public static void main(String[] args) {
        MinimumRemoveToMakeValidParentheses p = new MinimumRemoveToMakeValidParentheses();
        System.out.println(p.minRemoveToMakeValid("lee(t(c)o)de)")); // "lee(t(c)o)de" / "lee(t(co)de)" / "lee(t(c)ode)"
        System.out.println(p.minRemoveToMakeValid("a)b(c)d")); // "ab(c)d"
        System.out.println(p.minRemoveToMakeValid("))((")); // ""
    }

    public String minRemoveToMakeValid(String s) {
        LinkedHashMap<Integer, Character> m = new LinkedHashMap<>();
        Stack<Integer> stack = new Stack<>();
        char[] ch = s.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (c == '(') {
                stack.push(i);
                m.put(i, c);
            } else if (c == ')') {
                if (!stack.isEmpty()) {
                    m.put(i, c);
                    stack.pop();
                }
            } else {
                m.put(i, c);
            }
        }
        while (!stack.empty()) {
            m.remove(stack.pop());
        }
        char[] valid = new char[m.size()];
        int i = 0;
        for (Character c : m.values()) {
            valid[i++] = c;
        }
        return new String(valid);
    }
}

package com.ashx.leetcode.google.medium;

import java.util.Objects;
import java.util.Stack;

public class DecodeString {
    public static void main(String[] args) {
        System.out.println(decodeString("3[z]2[2[y]pq4[2[jk]e1[f]]]ef"));
        System.out.println(decodeStringRec("3[z]2[2[y]pq4[2[jk]e1[f]]]ef"));
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
        Stack<Integer> countStack = new Stack<>();
        Stack<StringBuilder> stringStack = new Stack<>();
        StringBuilder currentString = new StringBuilder();
        int k = 0;
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                k = k * 10 + ch - '0';
            } else if (ch == '[') {
                // push the number k to countStack
                countStack.push(k);
                // push the currentString to stringStack
                stringStack.push(currentString);
                // reset currentString and k
                currentString = new StringBuilder();
                k = 0;
            } else if (ch == ']') {
                StringBuilder decodedString = stringStack.pop();
                // decode currentK[currentString] by appending currentString k times
                for (int currentK = countStack.pop(); currentK > 0; currentK--) {
                    decodedString.append(currentString);
                }
                currentString = decodedString;
            } else {
                currentString.append(ch);
            }
        }
        return currentString.toString();
    }

    private static int index = 0;

    public static String decodeStringRec(String s) {
        StringBuilder result = new StringBuilder();
        while (index < s.length() && s.charAt(index) != ']') {
            if (!Character.isDigit(s.charAt(index))) {
                result.append(s.charAt(index++));
            } else {
                int k = 0;
                // build k while next character is a digit
                while (index < s.length() && Character.isDigit(s.charAt(index))) {
                    k = k * 10 + s.charAt(index++) - '0';
                }
                // ignore the opening bracket '['
                index++;
                String decodedString = decodeStringRec(s);
                // ignore the closing bracket ']'
                index++;
                // build k[decodedString] and append to the result
                while (k-- > 0) {
                    result.append(decodedString);
                }
            }
        }
        return new String(result);
    }
}

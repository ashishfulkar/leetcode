package com.ashx.leetcode.google.medium;

/**
 * <a href="https://leetcode.com/problems/basic-calculator-ii/">Leetcode</a>
 */
public class BasicCalculatorII {
    public static void main(String[] args) {
        BasicCalculatorII b = new BasicCalculatorII();
        // System.out.println(b.calculate("3+2*2")); // 7
        // System.out.println(b.calculate(" 3/2 ")); // 1
        // System.out.println(b.calculate(" 3+5 / 2 ")); // 5
        // System.out.println(b.calculate(" 3+5  ")); // 8
        // System.out.println(b.calculate("1+1+1")); // 3
        System.out.println(b.calculate("2*3+4")); // 10
        System.out.println(b.calculatePerf("1*2+3*4")); // 14
    }

    public int calculatePerf(String s) {
        int p = 0, num = 0, sum = 0;
        char o = '+';
        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                num *= 10;
                num += c - '0';
            } else if (c != ' ') {
                if (o == '+') {
                    sum += p;
                    p = num;
                } else if (o == '-') {
                    sum += p;
                    p = -num;
                } else if (o == '*') {
                    p *= num;
                } else if (o == '/') {
                    p /= num;
                }
                num = 0;
                o = c;
            }
        }

        return switch (o) {
            case '+' -> sum + p + num;
            case '-' -> sum + p - num;
            case '*' -> sum + p * num;
            default -> sum + p / num;
        };
    }

    public int calculate(String s) {
        int lastOpIdx = 0;
        char[] ch = s.toCharArray();

        // First resolve '*' and '/'
        StringBuilder s1 = new StringBuilder(); // Will only contain integers, '+' and '-'
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (c != ' ') {
                if (c == '+' || c == '-') {
                    lastOpIdx = s1.length();
                    s1.append(c);
                } else if (c == '*' || c == '/') {
                    String p1 = s1.substring(lastOpIdx == 0 ? 0 : lastOpIdx + 1);
                    i++;
                    StringBuilder p2 = new StringBuilder();
                    while (i < ch.length) {
                        char c1 = ch[i];
                        if (c1 != ' ') {
                            if (c1 == '+' || c1 == '-' || c1 == '/' || c1 == '*') {
                                break;
                            } else {
                                p2.append(c1);
                            }
                        }
                        i++;
                    }
                    i--;
                    int i1 = Integer.parseInt(p1), i2 = Integer.parseInt(p2.toString());
                    int ans = c == '*' ? i1 * i2 : i1 / i2;
                    s1 = new StringBuilder(s1.substring(0, lastOpIdx == 0 ? 0 : lastOpIdx + 1) + ans);
                } else {
                    s1.append(c);
                }
            }
        }

        // Resolve '+' and '-'
        int ans = 0, lastIntIdx = 0;
        int i = 0;
        for (; i < s1.length(); i++) {
            char c = s1.charAt(i);
            if (c == '+' || c == '-') {
                String p1 = s1.substring(lastIntIdx, i);
                ans = Integer.parseInt(p1); // Assign first integer to ans
                break;
            }
        }
        if (i == s1.length()) { // s doesn't contain '+' or '-'
            return Integer.parseInt(s1.toString());
        }
        for (; i < s1.length(); i++) {
            char c = s1.charAt(i);
            if (c == '+' || c == '-') {
                i++;
                StringBuilder p2 = new StringBuilder();
                while (i < s1.length()) {
                    char c1 = s1.charAt(i);
                    if (c1 == '+' || c1 == '-') {
                        break;
                    } else {
                        p2.append(c1);
                    }
                    i++;
                }
                i--;
                int i2 = Integer.parseInt(p2.toString());
                ans = c == '+' ? ans + i2 : ans - i2;
            }
        }

        return ans;
    }
}

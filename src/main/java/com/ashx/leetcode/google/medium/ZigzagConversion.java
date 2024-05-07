package com.ashx.leetcode.google.medium;

import java.util.ArrayList;

/**
 * <a href="https://leetcode.com/problems/zigzag-conversion/">Leetcode</a>
 */
public class ZigzagConversion {
    public static void main(String[] args) {
        ZigzagConversion c = new ZigzagConversion();
        System.out.println(c.convertPerf("PAYPALISHIRING", 3)); // PAHNAPLSIIGYIR
        System.out.println(c.convertPerf("PAYPALISHIRING", 4)); // PINALSIGYAHRPI
    }

    public String convertPerf(String s, int numRows) {
        int len = s.length();

        if (len <= numRows) {
            return s;
        }
        if (numRows == 1) {
            return s;
        }

        char[] chArr = s.toCharArray();

        char[] zigzagCharArr = new char[len];

        if (numRows == 2) {
            int x = 0;

            int i = 0;
            while (i < len) {
                zigzagCharArr[x++] = chArr[i];
                i += 2;
            }

            i = 1;
            while (i < len) {
                zigzagCharArr[x++] = chArr[i];
                i += 2;
            }

            String zigzagString = new String(zigzagCharArr);
            return zigzagString;
        } else if (numRows >= 3) {
            int i = 0, x = 0, k = 0;

            int inc = 2;
            int leap = (numRows - 1 - k) * 2;

            // get first row characters from (virtual) zigzag
            while (i < len) {
                zigzagCharArr[x++] = chArr[i];
                i += leap;
            }

            // get middle row characters from (virtual) zigzag
            k = 1;
            for (int j = 1; j <= numRows - 2; j++) {
                i = j;
                zigzagCharArr[x++] = chArr[i];
                leap = (numRows - 1 - k) * 2;
                i += leap;

                while (i < len) {
                    zigzagCharArr[x++] = chArr[i];

                    i += inc;
                    if (i >= len) {
                        break;
                    }
                    zigzagCharArr[x++] = chArr[i];
                    i += leap;
                }
                k += 1;
                inc += 2;
            }

            // get last row characters from (virtual) zigzag
            i = numRows - 1;
            leap = (numRows - 1 - 0) * 2;
            while (i < len) {
                zigzagCharArr[x++] = chArr[i];
                i += leap;
            }

            String zigzagString = new String(zigzagCharArr);
            return zigzagString;
        }
        return s;
    }

    public String convert(String s, int numRows) {
        if (numRows < 2) {
            return s;
        }
        ArrayList<Integer>[] rows = new ArrayList[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new ArrayList<>();
        }
        int idxNo = 0, rowNo = 0;
        boolean inc = true;
        while (idxNo < s.length()) {
            rows[rowNo].add(idxNo);
            if (inc && rowNo < numRows - 1) {
                rowNo++;
            } else if (inc) {
                rowNo--;
                inc = false;
            } else if (rowNo > 0) {
                rowNo--;
            } else {
                rowNo++;
                inc = true;
            }
            idxNo++;
        }

        StringBuilder b = new StringBuilder();
        for (ArrayList<Integer> row : rows) {
            for (int idx : row) {
                b.append(s.charAt(idx));
            }
        }
        return b.toString();
    }
}

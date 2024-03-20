package com.ashx.leetcode.google.medium;

import java.util.HashMap;

/**
 * <a href="https://leetcode.com/problems/longest-line-of-consecutive-one-in-matrix/">Leetcode</a>
 */
public class LongestLineOfConsecutiveOneInMatrix {
    public static void main(String[] args) {
        // @formatter:off
        /* int[][] mat = {
                {0, 1, 1, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 1}
        }; // 3 */
        /*int[][] mat = {
                {1, 1, 1, 1},
                {0, 1, 1, 0},
                {0, 0, 0, 1}
        }; // 4 */
        int[][] mat = {
                {1,1,0,0,1,0,0,1,1,0},
                {1,0,0,1,0,1,1,1,1,1},
                {1,1,1,0,0,1,1,1,1,0},
                {0,1,1,1,0,1,1,1,1,1},
                {0,0,1,1,1,1,1,1,1,0},
                {1,1,1,1,1,1,0,1,1,1},
                {0,1,1,1,1,1,1,0,0,1},
                {1,1,1,1,1,0,0,1,1,1},
                {0,1,0,1,1,0,1,1,1,1},
                {1,1,1,0,1,0,1,1,1,1}
        };
        // @formatter:on
        // int longestLine = longestLine(mat);
        // System.out.println(longestLine);
        System.out.println(longestLineBestSolution(mat));
    }

    public static int longestLine(int[][] mat) {
        int longestLine = 0;
        Node[][] matrix = getMatrix(mat);
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (matrix[row][col].val == 0) {
                    continue;
                }
                for (Direction direction : Direction.values()) {
                    longestLine = Math.max(recur(matrix, row, col, direction, 0), longestLine);
                }
            }
        }
        return longestLine;
    }

    private static int recur(Node[][] matrix, int row, int col, Direction direction, int cnt) {
        if (row < 0 || row == matrix.length || col < 0 || col == matrix[0].length || matrix[row][col].val == 0 || matrix[row][col].isDirectionSet(direction)) {
            return cnt;
        }

        cnt = recur(matrix, row + direction.row, col + direction.col, direction, ++cnt);
        matrix[row][col].setDirCount(direction, cnt);
        return cnt;
    }

    private static Node[][] getMatrix(int[][] mat) {
        int rows = mat.length, cols = mat[0].length;
        Node[][] matrix = new Node[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                matrix[row][col] = new Node(mat[row][col]);
            }
        }

        return matrix;
    }

    public static int longestLineBestSolution(int[][] mat) {
        int rows = mat.length, cols = mat[0].length;
        int res = 0;

        // horizontal
        for (int r = 0; r < rows; r++) {
            int ones = 0;
            for (int c = 0; c < cols; c++) {
                if (mat[r][c] == 0) {
                    ones = 0;
                } else {
                    ones++;
                    res = Math.max(ones, res);
                }
            }
        }

        // vertical
        for (int c = 0; c < cols; c++) {
            int ones = 0;
            for (int r = 0; r < rows; r++) {
                if (mat[r][c] == 0) {
                    ones = 0;
                } else {
                    ones++;
                    res = Math.max(res, ones);
                }
            }
        }

        // diagonal
        for (int r0 = 0; r0 < rows; r0++) {
            int ones = 0;
            for (int r = r0, c = 0; r < rows && c < cols; r++, c++) {
                if (mat[r][c] == 0) {
                    ones = 0;
                } else {
                    ones++;
                    res = Math.max(res, ones);
                }
            }

            ones = 0;
            for (int r = r0, c = cols - 1; r < rows && c >= 0; r++, c--) {
                if (mat[r][c] == 0) {
                    ones = 0;
                } else {
                    ones++;
                    res = Math.max(res, ones);
                }
            }
        }

        for (int c0 = 1; c0 < cols; c0++) {
            int ones = 0;
            for (int r = 0, c = c0; r < rows && c < cols; r++, c++) {
                if (mat[r][c] == 0) {
                    ones = 0;
                } else {
                    ones++;
                    res = Math.max(res, ones);
                }
            }

            ones = 0;
            for (int r = 0, c = c0; r < rows && c >= 0; r++, c--) {
                if (mat[r][c] == 0) {
                    ones = 0;
                } else {
                    ones++;
                    res = Math.max(res, ones);
                }
            }
        }


        return res;

    }

    enum Direction {
        RIGHT(0, 1), RIGHT_DOWN(1, 1), DOWN(1, 0), DOWN_LEFT(1, -1);

        final int row;
        final int col;

        Direction(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static class Node {
        int val;
        HashMap<Direction, Integer> dir;

        Node(int val) {
            this.val = val;
            dir = new HashMap<>();
        }

        void setDirCount(Direction direction, int count) {
            dir.put(direction, count);
        }

        boolean isDirectionSet(Direction direction) {
            return dir.containsKey(direction);
        }
    }
}
package com.ashx.leetcode.google.hard;

public class LongestIncreasingPathInAMatrix {
    public static void main(String[] args) {
//        int[][] matrix = {{9,9,4},{6,6,8},{2,1,1}};
        int[][] matrix = {{3, 4, 5}, {3, 2, 6}, {2, 2, 1}};
        System.out.println(longestIncreasingPath(matrix));
    }

    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static int longestIncreasingPath(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] cache = new int[rows][cols];
        int res = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                res = Math.max(res, recur(matrix, row, col, cache));
            }
        }

        return res;
    }

    private static int recur(int[][] matrix, int row, int col, int[][] cache) {
        if (cache[row][col] != 0) {
            return cache[row][col];
        }

        for (int[] dir : DIRECTIONS) {
            int nextRow = row + dir[0], nextCol = col + dir[1];
            if (0 <= nextRow && nextRow < matrix.length && 0 <= nextCol
                    && nextCol < matrix[0].length && matrix[nextRow][nextCol] > matrix[row][col]) {
                cache[row][col] = Math.max(cache[row][col], recur(matrix, nextRow, nextCol, cache));
            }
        }

        cache[row][col]++;
        return cache[row][col];
    }
}

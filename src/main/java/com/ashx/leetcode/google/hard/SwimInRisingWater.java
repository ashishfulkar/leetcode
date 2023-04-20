package com.ashx.leetcode.google.hard;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/swim-in-rising-water/">Leetcode</a>
 */
public class SwimInRisingWater {
    public static void main(String[] args) {
        int[][] grid = {
                {0, 1, 2, 3, 4},
                {24, 23, 22, 21, 5},
                {12, 13, 14, 15, 16},
                {11, 17, 18, 19, 20},
                {10, 9, 8, 7, 6}
        };
        System.out.println(swimInWater(grid));
    }

    public static int swimInWater(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] cache = new int[rows][cols];
        Arrays.stream(cache).forEach(arr -> Arrays.fill(arr, -1));
        rec(grid, 0, 0, cache);

        return cache[0][0];
    }

    private static final int[][] DIR = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private static int rec(int[][] grid, int row, int col, int[][] cache) {
        if (cache[row][col] != -1) {
            return cache[row][col];
        }
        int rows = grid.length;
        int cols = grid[0].length;
        if (row == rows - 1 && col == cols - 1) {
            cache[row][col] = grid[row][col];
        } else {
            int val = Integer.MAX_VALUE;
            for (int[] dir : DIR) {
                int nextRow = row + dir[0];
                int nextCol = col + dir[1];
                if (nextRow >= 0 && nextRow < rows
                        && nextCol >= 0 && nextCol < cols
                        && cache[nextRow][nextCol] < grid[row][col]) {
                    val = Math.min(val, rec(grid, nextRow, nextCol, cache));
                }
            }
            cache[row][col] = Math.max(grid[row][col], val);
        }

        return cache[row][col];
    }
}

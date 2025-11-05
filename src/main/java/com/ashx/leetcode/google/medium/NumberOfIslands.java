package com.ashx.leetcode.google.medium;

public class NumberOfIslands {
    public static void main(String[] args) {
        NumberOfIslands i = new NumberOfIslands();
        System.out.println(i.numIslands(new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        })); // 1

        System.out.println(i.numIslands(new char[][]{
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        })); // 3
    }

    public int numIslands(char[][] grid) {
        int count = 0;
        int rows = grid.length;
        if (rows == 0) {
            return 0;
        }
        int cols = grid[0].length;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++)
                if (grid[row][col] == '1') {
                    visit(grid, row, col);
                    ++count;
                }
        }
        return count;
    }

    private void visit(char[][] grid, int row, int col) {
        int rows = grid.length;
        int cols = grid[0].length;
        if (row < 0 || col < 0 || row >= rows || col >= cols || grid[row][col] != '1') {
            return;
        }
        grid[row][col] = '0';
        visit(grid, row + 1, col);
        visit(grid, row - 1, col);
        visit(grid, row, col + 1);
        visit(grid, row, col - 1);
    }
}

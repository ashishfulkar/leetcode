package com.ashx.leetcode.google.medium;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/game-of-life/">Leetcode</a>
 */
public class GameOfLife {
    private static final int[][] DIRECTIONS = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};

    public static void main(String[] args) {
        GameOfLife g = new GameOfLife();
        int[][] board = new int[][]{{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
        System.out.println("Before");
        print(board);
        g.gameOfLife(board);
        System.out.println("After");
        print(board);
    }

    private static void print(int[][] board) {
        for (int[] row : board) {
            for (int col : row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }
    }

    public void gameOfLife(int[][] board) {
        int[][] copy = Arrays.stream(board).map(int[]::clone).toArray(int[][]::new);
        int rows = board.length;
        int cols = board[0].length;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                board[row][col] = next(copy, row, col);
            }
        }
    }

    private int getLiveNeighbours(int[][] board, int row, int col) {
        int rows = board.length;
        int cols = board[0].length;
        int liveNeighbours = 0;
        for (int[] dir : DIRECTIONS) {
            int neighbourRow = row + dir[0];
            int neighbourCol = col + dir[1];
            if (neighbourRow >= 0 && neighbourRow < rows && neighbourCol >= 0 && neighbourCol < cols) {
                if (board[neighbourRow][neighbourCol] == 1) {
                    liveNeighbours++;
                }
            }
        }

        return liveNeighbours;
    }

    private int next(int[][] board, int row, int col) {
        int current = board[row][col];
        int liveNeighbours = getLiveNeighbours(board, row, col);
        return current == 1 && (liveNeighbours < 2 || liveNeighbours > 3) ? 0 : current == 0 && liveNeighbours == 3 ? 1 : current;
    }
}

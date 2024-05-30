package com.ashx.leetcode.google.medium;

/**
 * <a href="https://leetcode.com/problems/word-search/">Leetcode</a>
 */
public class WordSearch {
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static void main(String[] args) {
        WordSearch s = new WordSearch();

        System.out.println(s.exist(new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}}, "ABCCED")); // true
        System.out.println(s.exist(new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}}, "SEE")); // true
        System.out.println(s.exist(new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}}, "ABCB")); // false
        System.out.println(s.exist(new char[][]{{'A'}}, "A")); // true
        System.out.println(s.exist(new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'E', 'S'}, {'A', 'D', 'E', 'E'}}, "ABCEFSADEESE")); // true
    }

    public boolean exist(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;
        int len = word.length();
        if (rows * cols < len) {
            return false;
        }

        // Count the occurrence of each character on the board
        int[] counts = new int[123]; // a=97, z=122, A=65, Z=90 // Array to store counts of each character
        for (char[] chars : board) {
            for (char c : chars) {
                counts[c]++;
            }
        }
        // Decrease counts of characters in the word from the board
        for (char c : word.toCharArray()) {
            counts[c]--;
            if (counts[c] < 0) {
                return false; // If there are more occurrences of a character in the word than on the board, return false
            }
        }

        boolean[][] visited = new boolean[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (recur(board, visited, row, col, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean recur(char[][] board, boolean[][] visited, int row, int col, String word, int currChar) {
        if (currChar == word.length()) {
            return true;
        }
        if (row < 0 || row == board.length || col < 0 || col == board[0].length || visited[row][col] || word.charAt(currChar) != board[row][col]) {
            return false;
        }
        visited[row][col] = true;
        currChar++;

        boolean found = false;
        for (int[] direction : DIRECTIONS) {
            int nextRow = row + direction[0], nextCol = col + direction[1];
            found = found || recur(board, visited, nextRow, nextCol, word, currChar);
        }

        visited[row][col] = false;

        return found;
    }
}

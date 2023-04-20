package com.ashx.leetcode.google.easy;

/**
 * <a href="https://leetcode.com/problems/find-winner-on-a-tic-tac-toe-game/description/">Leetcode</a>
 */
public class FindWinnerOnATicTacToeGame {
    public static void main(String[] args) {
        int[][] moves = new int[][]{{0, 0}, {2, 0}, {1, 1}, {2, 1}, {2, 2}};
        String result = tictactoe(moves);
        System.out.println(result);
    }

    public static String tictactoe(int[][] moves) {
        char[][] mat = new char[3][3];
        for (int i = 0; i < moves.length; i++) {
            if (i % 2 == 0) {
                mat[moves[i][0]][moves[i][1]] = 'X';
                if (i >= 4 && win('X', mat)) {
                    return "A";
                }
            } else {
                mat[moves[i][0]][moves[i][1]] = 'O';
                if (i >= 4 && win('O', mat)) {
                    return "B";
                }
            }
        }
        return moves.length == 9 ? "Draw" : "Pending";
    }

    private static boolean win(char c, char[][] mat) {
        if (mat[0][0] == c && mat[0][1] == c && mat[0][2] == c) {
            return true; // first row
        }
        if (mat[1][0] == c && mat[1][1] == c && mat[1][2] == c) {
            return true; // second row
        }
        if (mat[2][0] == c && mat[2][1] == c && mat[2][2] == c) {
            return true; // third row
        }
        if (mat[0][0] == c && mat[1][0] == c && mat[2][0] == c) {
            return true; // first column
        }
        if (mat[0][1] == c && mat[1][1] == c && mat[2][1] == c) {
            return true; // second column
        }
        if (mat[0][2] == c && mat[1][2] == c && mat[2][2] == c) {
            return true; // third column
        }
        if (mat[0][0] == c && mat[1][1] == c && mat[2][2] == c) {
            return true; // first diagonal
        }
        return mat[0][2] == c && mat[1][1] == c && mat[2][0] == c; // second diagonal
    }
}

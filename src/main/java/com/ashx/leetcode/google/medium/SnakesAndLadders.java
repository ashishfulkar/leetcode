package com.ashx.leetcode.google.medium;

import java.util.HashMap;
import java.util.Stack;

/**
 * <a href="https://leetcode.com/problems/snakes-and-ladders/">Leetcode</a>
 */
public class SnakesAndLadders {
    public static void main(String[] args) {
        SnakesAndLadders l = new SnakesAndLadders();
        // @formatter:off
        System.out.println(l.snakesAndLaddersPerf(new int[][]{
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 35, -1, -1, 13, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 15, -1, -1, -1, -1}})); // 4
        /* System.out.println(l.snakesAndLadders(new int[][]{
                {-1, -1},
                {-1, 3}})); // 1 */
        /* System.out.println(l.snakesAndLadders(new int[][]{
                {-1, -1, -1, -1},
                {-1, -1, -1, -1},
                {-1, -1, -1, -1},
                {-1, -1, -1, -1}})); // 3 */
        System.out.println(l.snakesAndLaddersPerf(new int[][]{
                {-1, -1, -1},
                {-1, 9, 8},
                {-1, 8, 9}})); // 1
        // @formatter:on
    }

    public int snakesAndLaddersPerf(int[][] board) {
        int n = board.length, k = 1, steps = 0;
        int[] b = new int[n * n + 1];
        int[] map = new int[n * n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = (i % 2 == 0) ? 0 : n - 1; j >= 0 && j < n; ) {
                b[k++] = board[n - i - 1][j];
                if (i % 2 == 0) j++;
                else j--;
            }
        }
        Stack<Integer> stack = new Stack<>();
        stack.push(1);

        while (!stack.isEmpty()) {
            Stack<Integer> temp = new Stack<>();
            while (!stack.isEmpty()) {
                int curr = stack.pop();
                for (int i = curr + 1; i <= curr + 6; i++) {
                    int ind = (b[i] == -1) ? i : b[i];
                    if (map[ind] == 0) {
                        map[ind] = steps + 1;
                        if (ind == n * n) {
                            return map[ind];
                        }
                        temp.push(ind);
                    }
                }
            }
            stack = temp;
            steps++;
        }
        return -1;
    }

    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        if (n < 3) { // (max squares are 2 * 2 = 4) < 6 (highest number in die)
            return 1; // only 1 move is required
        }

        Square[][] b = new Square[n][n];
        HashMap<Integer, Square> map = new HashMap<>();
        int num = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                b[i][j] = new Square(num, board[i][j]);
                map.put(num, b[i][j]);
                num++;
            }
            i--;
            if (i < 0) {
                break;
            }
            for (int j = n - 1; j >= 0; j--) {
                b[i][j] = new Square(num, board[i][j]);
                map.put(num, b[i][j]);
                num++;
            }
        }

        return recur(b, map, 1);
    }

    private int recur(Square[][] board, HashMap<Integer, Square> map, int currentNum) {
        int n = board.length;
        if (currentNum > n * n) {
            return 0;
        }
        Square current = map.get(currentNum);
        if (current.num == n * n) {
            return 0;
        }
        if (current.visited) {
            return 0;
        }
        if (current.minMoves > 0) {
            return current.minMoves;
        }
        current.visited = true;
        int moves = Integer.MAX_VALUE;
        if (current.nextNum != -1) {
            moves = recur(board, map, current.nextNum) + 1;
        }
        for (int i = 1; i <= 6; i++) {
            moves = Math.min(moves, recur(board, map, current.num + i) + 1);
        }

        current.minMoves = moves;
        current.visited = false;

        return current.minMoves;
    }

    static class Square {
        int num, nextNum, minMoves;
        boolean visited;

        Square(int num, int nextNum) {
            this.num = num;
            this.nextNum = nextNum;
            this.minMoves = -1;
            this.visited = false;
        }
    }
}

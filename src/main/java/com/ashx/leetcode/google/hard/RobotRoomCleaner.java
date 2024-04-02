package com.ashx.leetcode.google.hard;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * This is the robot's control interface.
 * You should not implement it, or speculate about its implementation
 */
interface Robot {
    // Returns true if the cell in front is open and robot moves into the cell.
    // Returns false if the cell in front is blocked and robot stays in the current cell.
    public boolean move();

    // Robot will stay in the same cell after calling turnLeft/turnRight.
    // Each turn will be 90 degrees.
    public void turnLeft();

    public void turnRight();

    // Clean the current cell.
    public void clean();
}

/**
 * <a href="https://leetcode.com/problems/robot-room-cleaner/">Leetcode</a>
 */
public class RobotRoomCleaner {
    // going clockwise : 0: 'up', 1: 'right', 2: 'down', 3: 'left'
    int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    Set<Pair> visited = new HashSet<>();
    Robot robot;
    HashSet<String> visitedStr = new HashSet<>();

    public void goBack() {
        robot.turnRight();
        robot.turnRight();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    }

    public void backtrack(int row, int col, int d) {
        visited.add(Pair.of(row, col));
        robot.clean();
        // going clockwise : 0: 'up', 1: 'right', 2: 'down', 3: 'left'
        for (int i = 0; i < 4; ++i) {
            int newD = (d + i) % 4;
            int newRow = row + directions[newD][0];
            int newCol = col + directions[newD][1];

            if (!visited.contains(Pair.of(newRow, newCol)) && robot.move()) {
                backtrack(newRow, newCol, newD);
                goBack();
            }
            // turn the robot following chosen direction : clockwise
            robot.turnRight();
        }
    }

    public void cleanRoom(Robot robot) {
        this.robot = robot;
        backtrack(0, 0, 0);
    }

    public void cleanRoomPerf(Robot robot) {
        clean(robot, 0, 0, 0);
    }

    private void clean(Robot robot, int x, int y, int curDirection) {
        // Cleans current cell.
        robot.clean();
        visitedStr.add(x + " " + y);

        for (int nDirection = curDirection; nDirection < curDirection + 4; nDirection++) {
            int nx = directions[nDirection % 4][0] + x;
            int ny = directions[nDirection % 4][1] + y;
            if (!visitedStr.contains(nx + " " + ny) && robot.move()) {
                clean(robot, nx, ny, nDirection % 4);
            }
            // Changed orientation.
            robot.turnRight();
        }

        // Moves backward one step while maintaining the orientation.
        goBack();
    }

    static class Pair {
        int f1, f2;

        static Pair of(int f1, int f2) {
            Pair pair = new Pair();
            pair.f1 = f1;
            pair.f2 = f2;
            return pair;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;
            if (!(obj instanceof Pair)) return false;
            Pair p = (Pair) obj;
            return this.f1 == p.f1 && this.f2 == p.f2;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.f1, this.f2);
        }
    }
}
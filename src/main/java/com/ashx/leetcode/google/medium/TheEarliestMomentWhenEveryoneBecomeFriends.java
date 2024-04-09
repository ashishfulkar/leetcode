package com.ashx.leetcode.google.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * <a href="https://leetcode.com/problems/the-earliest-moment-when-everyone-become-friends/">Leetcode</a>
 */
public class TheEarliestMomentWhenEveryoneBecomeFriends {
    public static void main(String[] args) {
        TheEarliestMomentWhenEveryoneBecomeFriends f = new TheEarliestMomentWhenEveryoneBecomeFriends();
        // int[][] logs = {{20190101, 0, 1}, {20190104, 3, 4}, {20190107, 2, 3}, {20190211, 1, 5}, {20190224, 2, 4}, {20190301, 0, 3}, {20190312, 1, 2}, {20190322, 4, 5}};
        // int n = 6;
        // int[][] logs = {{0, 2, 0}, {1, 0, 1}, {3, 0, 3}, {4, 1, 2}, {7, 3, 1}};
        // int n = 4;
        int[][] logs = {{5, 3, 2}, {3, 0, 1}, {2, 2, 0}, {10, 2, 4}, {0, 3, 1}, {8, 3, 4}};
        int n = 5;
        System.out.println(f.earliestAcqPerf(logs, n));
    }

    public int earliestAcq(int[][] logs, int n) {
        Arrays.sort(logs, Comparator.comparingInt(o -> o[0]));
        ArrayList<ArrayList<Integer>> groups = new ArrayList<>();
        for (int[] log : logs) {
            ArrayList<Integer> group1 = null, group2 = null;
            for (ArrayList<Integer> group : groups) {
                if (group.contains(log[1])) {
                    group1 = group;
                }
                if (group.contains(log[2])) {
                    group2 = group;
                }
                if (group1 != null && group2 != null) {
                    break;
                }
            }
            if (group1 != null && group2 != null && !group1.equals(group2)) {
                group1.addAll(group2);
                if (group1.size() == n) {
                    return log[0];
                }
                groups.remove(group2);
            } else if (group1 == null && group2 != null) {
                group2.add(log[1]);
                if (group2.size() == n) {
                    return log[0];
                }
            } else if (group2 == null && group1 != null) {
                group1.add(log[2]);
                if (group1.size() == n) {
                    return log[0];
                }
            } else if (group1 == null) {
                ArrayList<Integer> group = new ArrayList<>();
                group.add(log[1]);
                group.add(log[2]);
                if (group.size() == n) {
                    return log[0];
                }
                groups.add(group);
            }
        }
        return -1;
    }

    public int earliestAcqBest(int[][] logs, int n) {
        // Timestamps - are these dates, just seconds/mins/hours
        // a ,b --> TSa TSb
        // TSa - TSb =

        // Return the Time Stamp - Earliest Time all graph nodes merge.

        // Min Heap DS - All logs in it.
        // Create UnionFind by Size DS.
        // Pop Heap 1by1. -will get minimum first - union u,v.
        // always check if all are merged - if yes break and return ans.

        int ans = -1;

        DisjointSet ds = new DisjointSet(n);

        PriorityQueue<Logs> minHeap = new PriorityQueue<>();

        for (int[] log : logs) {
            minHeap.offer(new Logs(log));
        }

        while (!minHeap.isEmpty()) {
            Logs cur = minHeap.poll();

            int timeStamp = cur.timeStamp;
            int u = cur.u;
            int v = cur.v;

            // Union of u,v
            ds.unionBySize(u, v);

            // Check whether all nodes are done.
            if (allFriendsAcquainted(ds, ds.findUltimatePar(u), n)) {
                return timeStamp;
            }
        }
        return ans;
    }

    private boolean allFriendsAcquainted(DisjointSet ds, int parent, int n) {
        for (int i = 0; i < n; i++) {
            if (ds.findUltimatePar(i) != parent) {
                return false;
            }
        }
        return true;
    }

    private int find(int[] par, int u) {
        if (par[u] != u) par[u] = find(par, par[u]);
        return par[u];
    }

    private boolean join(int[] par, int x, int y) {
        int p1 = find(par, x), p2 = find(par, y);
        if (p1 == p2) return false;
        if (p1 > p2) par[p1] = p2;
        else par[p2] = p1;
        return true;
    }

    public int earliestAcqPerf(int[][] logs, int n) {
        int[] par = new int[n];
        for (int i = 0; i < n; i++) par[i] = i;
        Arrays.sort(logs, Comparator.comparingInt(a -> a[0]));
        for (int[] log : logs) {
            if (join(par, log[1], log[2])) n--;
            if (n == 1) return log[0];
        }
        return -1;
    }
}

class Logs implements Comparable<Logs> {
    int timeStamp;
    int u;
    int v;

    public Logs(int[] logs) {
        this.timeStamp = logs[0];
        this.u = logs[1];
        this.v = logs[2];
    }

    @Override
    public int compareTo(Logs logs) {
        return this.timeStamp - logs.timeStamp;
    }

    @Override
    public String toString() {
        return this.timeStamp + " :" + u + " - " + v;
    }
}

class DisjointSet {
    int[] size;
    int[] par;

    public DisjointSet(int n) {
        size = new int[n];
        par = new int[n];
        for (int i = 0; i < n; i++) {
            par[i] = i;
            size[i] = 1;
        }
    }

    public int findUltimatePar(int node) {
        if (par[node] == node) {
            return node;
        }
        return par[node] = findUltimatePar(par[node]);
    }

    public void unionBySize(int u, int v) {
        int ultimateUPar = findUltimatePar(u);
        int ultimateVPar = findUltimatePar(v);

        if (ultimateUPar == ultimateVPar) {
            return;
        }

        if (size[ultimateUPar] > size[ultimateVPar]) {
            size[ultimateUPar] += size[ultimateVPar];
            par[ultimateVPar] = ultimateUPar;
        } else if (size[ultimateUPar] <= size[ultimateVPar]) {
            size[ultimateVPar] += size[ultimateUPar];
            par[ultimateUPar] = ultimateVPar;
        }
    }
}
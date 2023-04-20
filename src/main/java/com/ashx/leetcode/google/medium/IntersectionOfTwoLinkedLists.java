package com.ashx.leetcode.google.medium;

import com.ashx.leetcode.google.model.TreeNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;

/**
 * <a href="https://leetcode.com/problems/intersection-of-two-linked-lists/">Leetcode</a>
 */
public class IntersectionOfTwoLinkedLists {
    public static void main(String[] args) throws IOException {
//        multipleIntersection();
        twoListsIntersection();
    }

    public static void twoListsIntersection() {
        TreeNode intersectingNode = new TreeNode(7);
        intersectingNode.right = new TreeNode(8);
        // 7->8

        TreeNode headA = new TreeNode(5);
        headA.right = new TreeNode(6);
        headA.right.right = intersectingNode;
        // 5->6->7->8

        TreeNode headB = new TreeNode(4);
        headB.right = intersectingNode;
        // 4->7->8

        TreeNode n = getIntersectionNode(headA, headB);
        System.out.println(n == null ? "null" : n.val);
    }

    public static TreeNode getIntersectionNode(TreeNode headA, TreeNode headB) {
        //boundary check
        if (headA == null || headB == null) {
            return null;
        }

        TreeNode a = headA;
        TreeNode b = headB;

        //if a & b have different len, then we will stop the loop after second iteration
        while (a != b) {
            //for the end of first iteration, we just reset the pointer to the head of another linkedlist
            a = a == null ? headB : a.right;
            b = b == null ? headA : b.right;
        }

        return a;
    }

    public static void multipleIntersection() throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(reader);
        String line;
        HashMap<String, String> map = new HashMap<>();
        while ((line = in.readLine()) != null) {
            // System.out.println(line);
            if (line.contains("->")) {
                String[] nodes = line.split("->");
                populate(nodes[0], nodes[1], map);
            } else {
                String[] nodes = line.split(",");
                boolean intersectionFound = findIntersection(nodes, map);
                System.out.println(intersectionFound ? "True" : "False");
            }
        }
        /*HashMap<String, String> map = new HashMap<>();
        map.put("A", "B");
        map.put("G", "B");
        map.put("B", "C");
        map.put("C", "D");
        map.put("D", "E");
        map.put("H", "J");
        map.put("J", "F");
        A->B->C->D->E
        G->B->C->D->E
        H->J->F
        String[] nodes = {"H", "A"};
        System.out.println(findIntersection(nodes, map));*/
    }

    private static void populate(String from, String to, HashMap<String, String> map) {
        if (map.containsKey(from)) {
            throw new IllegalArgumentException("Cycle detected.");
        }
        map.put(from, to);
    }

    private static boolean findIntersection(String[] nodes, HashMap<String, String> map) {
        boolean intersectionFound = false;
        HashSet<String> visited = new HashSet<>();
        for (int i = 0; i < nodes.length && !intersectionFound; i++) {
            String curr = nodes[i];
            if (visited.contains(curr)) {
                intersectionFound = true;
            } else {
                visited.add(curr);
                String next = map.get(curr);
                while (next != null && !intersectionFound) {
                    if (visited.contains(next)) {
                        intersectionFound = true;
                    } else {
                        visited.add(next);
                        next = map.get(next);
                    }
                }
            }
        }

        return intersectionFound;
    }
}

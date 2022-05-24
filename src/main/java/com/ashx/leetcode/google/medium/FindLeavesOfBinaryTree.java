package com.ashx.leetcode.google.medium;

import com.ashx.leetcode.google.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class FindLeavesOfBinaryTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = null;
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(3);
        root.left.left.left = new TreeNode(1);
        List<List<Integer>> res = findLeaves(root);
        print(res);
    }

    private static void print(List<List<Integer>> res) {
        res.forEach(System.out :: println);
    }

    public static List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        recur(root, res);
        return res;
    }

    private static int recur(TreeNode root, List<List<Integer>> res) {
        if (root == null) {
            return -1;
        } else {
            int ht = 1 + Math.max(recur(root.left, res), recur(root.right, res));
            if (res.size() < ht + 1) {
                res.add(new ArrayList<>());
            }
            res.get(ht).add(root.val);
            return ht;
        }
    }
}

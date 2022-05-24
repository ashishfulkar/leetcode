package com.ashx.leetcode.google.model;

import java.util.Objects;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof TreeNode)) {
            return false;
        }
        TreeNode a = this;
        TreeNode b = (TreeNode) o;

        return equals(a, b);
    }

    public static boolean equals(TreeNode a, TreeNode b) {
        /*1. both empty */
        if (a == null && b == null) {
            return true;
        }
        /* 2. both non-empty -> compare them */
        if (a != null && b != null) {
            return Objects.equals(a.val, b.val)
                    && equals(a.left, b.left)
                    && equals(a.right, b.right);
        }

        /* 3. one empty, one not -> false */
        return false;
    }
}

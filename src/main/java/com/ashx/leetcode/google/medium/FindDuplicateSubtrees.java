package com.ashx.leetcode.google.medium;

import com.ashx.leetcode.google.model.TreeNode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * https://leetcode.com/problems/find-duplicate-subtrees/
 */
public class FindDuplicateSubtrees {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.right.left = new TreeNode(3);

        List<TreeNode> duplicates = findDuplicateSubtrees(root);
        for (TreeNode node : duplicates) {
            System.out.println(inorder(node));
        }
    }

    public static List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Set<TreeNode> set = new TreeSet<>(new TreeNodeComparator());
        Set<TreeNode> duplicates = new TreeSet<>(new TreeNodeComparator());
        inorder(root, set, duplicates);

        return new ArrayList<>(duplicates);
    }

    private static void inorder(TreeNode root, Set<TreeNode> set, Set<TreeNode> duplicates) {
        if (root != null) {
            inorder(root.left, set, duplicates);
            if (!set.add(root)) {
                duplicates.add(root);
            }
            inorder(root.right, set, duplicates);
        }
    }

    private static class TreeNodeComparator implements Comparator<TreeNode> {
        @Override
        public int compare(TreeNode a, TreeNode b) {
            if (a == null && b == null) {
                return 0;
            } else if (a == null) {
                return -1;
            } else if (b == null) {
                return 1;
            } else {
                int cmp = Integer.compare(a.val, b.val);
                if (cmp == 0) {
                    cmp = compare(a.left, b.left);
                    if (cmp == 0) {
                        cmp = compare(a.right, b.right);
                    }
                }
                return cmp;
            }
        }
    }

    public static List<Integer> inorder(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        return list;
    }

    private static void inorder(TreeNode root, List<Integer> list) {
        if (root != null) {
            inorder(root.left, list);
            list.add(root.val);
            inorder(root.right, list);
        }
    }
}

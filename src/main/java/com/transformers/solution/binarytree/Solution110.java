package com.transformers.solution.binarytree;

public class Solution110 {
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!isBalanced(root.left) || !isBalanced(root.right)) {
            return false;
        }
        int leftDepth = maxDepth(root.left) + 1;
        int rightDepth = maxDepth(root.right) + 1;
        if (Math.abs(leftDepth - rightDepth) > 1) {
            return false;
        }
        return true;
    }

    private int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}

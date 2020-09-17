package com.transformers.solution.binarytree;

public class Solution98 {
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isBST(TreeNode root, long minValue, long maxValue) {
        if (root == null) {
            return true;
        }
        if (minValue >= root.val || maxValue <= root.val) {
            return false;
        }
        return isBST(root.left, minValue, root.val) && isBST(root.right, root.val, maxValue);
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

package com.transformers.solution.binarytree;

public class Solution222 {
//    public int countNodes(TreeNode root) {
//        return root == null ? 0 : countNodes(root.left) + countNodes(root.right) + 1;
//    }

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = countDepth(root.left);
        int rightDepth = countDepth(root.right);
        if (leftDepth == rightDepth) {
            // 左右子树高度相等，左子树为满二叉树，右子树为完全二叉树
            return countNodes(root.right) + (1 << leftDepth);
        } else {
            // 不相等，右子树为满二叉树，左子树为完全二叉树
            return countNodes(root.left) + (1 << rightDepth);
        }
    }

    private int countDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int depth = 0;
        while (root != null) {
            depth++;
            root = root.left;
        }
        return depth;
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

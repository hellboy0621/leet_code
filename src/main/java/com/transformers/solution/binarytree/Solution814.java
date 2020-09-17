package com.transformers.solution.binarytree;

public class Solution814 {

    public TreeNode pruneTree(TreeNode root) {
        return handleDel(root);
    }

    // 在递归的过程中，如果当前结点的左右节点皆为空，且当前结点为0，我们就将当前节点剪掉即可。
    private TreeNode handleDel(TreeNode root) {
        if (root == null) {
            return null;
        }
        root.left = handleDel(root.left);
        root.right = handleDel(root.right);
        if (root.left == null && root.right == null && root.val == 0) {
            return null;
        }
        return root;
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

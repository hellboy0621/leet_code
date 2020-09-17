package com.transformers.solution.binarytree;

public class Solution450 {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
            return root;
        }
        if (key > root.val) {
            root.right = deleteNode(root.right, key);
            return root;
        }
        // 找到待删节点
        // 待删节点右子树为空，让待删节点左子树代替自己
        if (root.right == null) {
            return root.left;
        }
        // 待删节点左子树为空，让待删节点右子树代替自己
        if (root.left == null) {
            return root.right;
        }
        // 待删节点左右子树都不为空
        // 找到待删节点右子树中最小的节点，即待删节点的后继，来代替自己
        TreeNode minNode = root.right;
        while (minNode.left != null) {
            // 查找后继
            minNode = minNode.left;
        }
        root.val = minNode.val;
        root.right = deleteMinNode(root.right);
        return root;
    }

    private TreeNode deleteMinNode(TreeNode root) {
        if (root.left == null) {
            TreeNode tmpNode = root.right;
            root.right = null;
            return tmpNode;
        }
        root.left = deleteMinNode(root.left);
        return root;
    }

    public TreeNode deleteNode2(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
            return root;
        }
        if (key > root.val) {
            root.right = deleteNode(root.right, key);
            return root;
        }
        // 找到待删节点
        // 待删节点右子树为空，让待删节点左子树代替自己
        if (root.right == null) {
            return root.left;
        }
        // 待删节点左子树为空，让待删节点右子树代替自己
        if (root.left == null) {
            return root.right;
        }
        // 待删节点左右子树都不为空
        // 找到待删节点左子树中最大的节点，即待删节点的前继，来代替自己
        TreeNode maxNode = root.left;
        while (maxNode.right != null) {
            // 查找后继
            maxNode = maxNode.right;
        }
        root.val = maxNode.val;
        root.left = deleteMaxNode(root.left);
        return root;
    }

    private TreeNode deleteMaxNode(TreeNode root) {
        if (root.right == null) {
            TreeNode tmpNode = root.left;
            root.left = null;
            return tmpNode;
        }
        root.right = deleteMaxNode(root.right);
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

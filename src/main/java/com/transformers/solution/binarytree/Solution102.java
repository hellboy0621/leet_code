package com.transformers.solution.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> levelList = new ArrayList<>();
            ret.add(levelList);
            while (size > 0) {
                TreeNode node = queue.poll();
                levelList.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                size--;
            }
        }
        return ret;
    }

    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        return dfs(root, 0, ret);
    }

    private List<List<Integer>> dfs(TreeNode root, int level, List<List<Integer>> ret) {
        if (root == null) {
            return ret;
        }
        if (ret.size() == level) {
            List<Integer> levelList = new ArrayList<>();
            levelList.add(root.val);
            ret.add(levelList);
        } else {
            ret.get(level).add(root.val);
        }
        dfs(root.left, level + 1, ret);
        dfs(root.right, level + 1, ret);
        return ret;
    }

    public static void main(String[] args) {
        new Solution102().test();
    }

    public void test() {
        // [3,9,20,null,null,15,7]
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        levelOrder2(root);
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}

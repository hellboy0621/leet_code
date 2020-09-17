package com.transformers.solution.binarytree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Solution104 {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    // 深度优先搜索算法 DFS Depth Fist Search
    // 错误，因为使用栈，先进后出，FILO，计算层次时错误
    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        int deep = 0;
        while (!stack.empty()) {
            int size = stack.size();
            while (size > 0) {
                TreeNode node = stack.pop();
                if (node.right != null) {
                    stack.push(node.right);
                }
                if (node.left != null) {
                    stack.push(node.left);
                }
                size--;
            }
            deep++;
        }
        return deep;
    }

    // 使用队列，入队时从队尾加入，保证一层一层的处理
    public int maxDepth3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int deep = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode node = queue.poll();
                if (node.right != null) {
                    queue.offer(node.right);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                size--;
            }
            deep++;
        }
        return deep;
    }

    public static void main(String[] args) {
        new Solution104().test();
    }

    public void test() {
        // [1,2,3,4,null,null,5]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        maxDepth2(root);
        maxDepth3(root);
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

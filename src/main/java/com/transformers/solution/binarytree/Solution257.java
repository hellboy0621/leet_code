package com.transformers.solution.binarytree;

import java.util.ArrayList;
import java.util.List;

public class Solution257 {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ret = new ArrayList<>();
        constructPaths(root, "", ret);
        return ret;
    }

    private void constructPaths(TreeNode root, String path, List<String> ret) {
        if (root != null) {
            path += root.val;
            if (root.left == null && root.right == null) {
                ret.add(path);
            } else {
                path += "->";
                constructPaths(root.left, path, ret);
                constructPaths(root.right, path, ret);
            }
        }
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

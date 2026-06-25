package binarytree.lc102;

/**
 * 类说明：
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026-06-22 14:36
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(){}
    TreeNode(int val){
        this.val=val;
    }
    TreeNode(int val,TreeNode left,TreeNode right){
        this.val=val;
        this.left=left;
        this.right=right;
    }

}

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        // 1. 判空，空树直接返回
        if (root == null) return result;

        // 队列：存放待遍历的二叉树节点，迭代核心容器
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root); // 根节点先入队

        // 迭代外层：队列不为空，说明还有层需要遍历
        while (!queue.isEmpty()) {
            // 关键：记录当前这一层一共有多少个节点（迭代分层核心）
            int currentLevelCount = queue.size();
            List<Integer> singleLevel = new ArrayList<>();

            // 迭代内层：遍历当前层所有节点
            for (int i = 0; i < currentLevelCount; i++) {
                // 队头节点出队
                TreeNode curr = queue.poll();
                singleLevel.add(curr.val);

                // 左孩子存在 → 入队（下一层待遍历）
                if (curr.left != null) {
                    queue.add(curr.left);
                }
                // 右孩子存在 → 入队（下一层待遍历）
                if (curr.right != null) {
                    queue.add(curr.right);
                }
            }
            // 当前层遍历完毕，存入结果
            result.add(singleLevel);
        }
        return result;
    }
}
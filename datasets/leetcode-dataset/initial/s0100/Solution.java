package g0001_0100.s0100_same_tree;

// #Easy #Depth_First_Search #Breadth_First_Search #Tree #Binary_Tree #Level_2_Day_15_Tree
// #Udemy_Tree_Stack_Queue #2022_06_21_Time_0_ms_(100.00%)_Space_40.9_MB_(78.42%)

import com_github_leetcode.TreeNode;

/*
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
public class Solution {
//@ ensures(*Given the roots of two binary trees p and q, write a function to check if they are the same or not.
Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.*);

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null || q == null) {
            return p == null && q == null;
        }
        boolean b1 = isSameTree(p.left, q.left);
        boolean b2 = isSameTree(p.right, q.right);
        return p.val == q.val && b1 && b2;
    }
}
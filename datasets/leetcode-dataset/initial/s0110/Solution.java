package g0101_0200.s0110_balanced_binary_tree;

// #Easy #Depth_First_Search #Tree #Binary_Tree #Programming_Skills_II_Day_2 #Level_2_Day_6_Tree
// #Udemy_Tree_Stack_Queue #2022_06_22_Time_1_ms_(98.82%)_Space_44.8_MB_(19.20%)

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
<<<<<<< HEAD
=======
//@ ensures(*Given a binary tree, determine if it is height-balanced. For this problem, a height-balanced binary tree is defined as: a binary tree in which the left and right subtrees of every node differ in height by no more than 1.*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
    public boolean isBalanced(TreeNode root) {
        // Empty Tree is balanced
        if (root == null) {
            return true;
        }
        // Get max height of subtree child
        // Get max height of subtree child
        // compare height difference (cannot be more than 1)
        int leftHeight = 0;
        int rightHeight = 0;
        if (root.left != null) {
            leftHeight = getMaxHeight(root.left);
        }
        if (root.right != null) {
            rightHeight = getMaxHeight(root.right);
        }
        int heightDiff = Math.abs(leftHeight - rightHeight);
        // if passes height check
        //  - Check if left subtree is balanced and if the right subtree is balanced
        //  - If one of both are imbalanced, then the tree is imbalanced
        return heightDiff <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    private int getMaxHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = 0;
        int rightHeight = 0;
        // Left
        if (root.left != null) {
            leftHeight = getMaxHeight(root.left);
        }
        // Right
        if (root.right != null) {
            rightHeight = getMaxHeight(root.right);
        }
        if (leftHeight > rightHeight) {
            return 1 + leftHeight;
        } else {
            return 1 + rightHeight;
        }
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03

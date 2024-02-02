package g1301_1400.s1315_sum_of_nodes_with_even_valued_grandparent;

// #Medium #Depth_First_Search #Breadth_First_Search #Tree #Binary_Tree
// #2022_03_18_Time_2_ms_(78.61%)_Space_52.9_MB_(21.61%)

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
//@ ensures(*Given the `root` of a binary tree, return _the sum of values of nodes with an **even-valued grandparent**_. If there are no nodes with an **even-valued grandparent**, return `0`.*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
    public int sumEvenGrandparent(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return dfs(root, root.left, 0) + dfs(root, root.right, 0);
    }

    private int dfs(TreeNode grandparent, TreeNode parent, int sum) {
        if (grandparent == null || parent == null) {
            return sum;
        }
        if (grandparent.val % 2 == 0 && parent.left != null) {
            sum += parent.left.val;
        }
        if (grandparent.val % 2 == 0 && parent.right != null) {
            sum += parent.right.val;
        }
        sum = dfs(parent, parent.left, sum);
        sum = dfs(parent, parent.right, sum);
        return sum;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03

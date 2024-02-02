package g0501_0600.s0501_find_mode_in_binary_search_tree;

// #Easy #Depth_First_Search #Tree #Binary_Tree #Binary_Search_Tree
// #2022_07_24_Time_1_ms_(92.14%)_Space_46_MB_(55.61%)

import com_github_leetcode.TreeNode;
import java.util.ArrayList;
import java.util.List;

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
    private Integer prev = null;
    private int max = 0;
    private int cnt = 1;
<<<<<<< HEAD
=======
//@ ensures(*Given the `root` of a binary search tree (BST) with duplicates, return _all the [mode(s)](https://en.wikipedia.org/wiki/Mode_(statistics)) (i.e., the most frequently occurred element) in it_. If the tree has more than one mode, return them in **any order**.*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03

    public int[] findMode(TreeNode root) {
        ArrayList<Integer> modes = new ArrayList<>();
        traverse(root, modes);
        int[] res = new int[modes.size()];
        for (int i = 0; i < modes.size(); i++) {
            res[i] = modes.get(i);
        }
        return res;
    }

    private void traverse(TreeNode root, List<Integer> modes) {
        if (root == null) {
            return;
        }
        traverse(root.left, modes);
        if (prev != null) {
            if (prev == root.val) {
                cnt++;
            } else {
                cnt = 1;
            }
        }
        if (cnt > max) {
            max = cnt;
            modes.clear();
            modes.add(root.val);
        } else if (cnt == max) {
            modes.add(root.val);
        }
        prev = root.val;
        traverse(root.right, modes);
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03

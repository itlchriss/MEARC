package g2401_2500.s2458_height_of_binary_tree_after_subtree_removal_queries;

// #Hard #Array #Depth_First_Search #Breadth_First_Search #Tree #Binary_Tree
// #2022_12_20_Time_52_ms_(87.45%)_Space_70.6_MB_(98.17%)

import com_github_leetcode.TreeNode;
import java.util.HashMap;
import java.util.Map;

public class Solution {
<<<<<<< HEAD
=======
//@ ensures(*initial prepare.sh run.sh The queries are independent, so the tree returns to its initial state after each query.
Return _an array_ `answer` _of size_ `m` _where_ `answer[i]` _is the height of the tree after performing the_ <code>i<sup>th</sup></code> _query_.*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
    public int[] treeQueries(TreeNode root, int[] queries) {
        Map<Integer, int[]> levels = new HashMap<>();
        Map<Integer, int[]> map = new HashMap<>();
        int max = dfs(root, 0, map, levels) - 1;
        int n = queries.length;
        for (int i = 0; i < n; i++) {
            int q = queries[i];
            int[] node = map.get(q);
            int height = node[0];
            int level = node[1];
            int[] lev = levels.get(level);
            if (lev[0] == height) {
                if (lev[1] != -1) {
                    queries[i] = max - Math.abs(lev[0] - lev[1]);
                } else {
                    queries[i] = max - height - 1;
                }
            } else {
                queries[i] = max;
            }
        }
        return queries;
    }

    private int dfs(TreeNode root, int level, Map<Integer, int[]> map, Map<Integer, int[]> levels) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left, level + 1, map, levels);
        int right = dfs(root.right, level + 1, map, levels);
        int height = Math.max(left, right);
        int[] lev = levels.getOrDefault(level, new int[] {-1, -1});
        if (height >= lev[0]) {
            lev[1] = lev[0];
            lev[0] = height;
        } else {
            lev[1] = Math.max(lev[1], height);
        }
        levels.put(level, lev);
        map.put(root.val, new int[] {height, level});
        return Math.max(left, right) + 1;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03

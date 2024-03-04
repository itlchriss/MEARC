package g2301_2400.s2392_build_a_matrix_with_conditions;

// #Hard #Array #Matrix #Graph #Topological_Sort
// #2022_09_02_Time_9_ms_(97.22%)_Space_50.2_MB_(99.69%)

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Solution {
    // Using topological sort to solve this problem
//@ ensures(*Preconditions:*);
//@ ensures(*The input integer `k` is a positive integer.*);
//@ ensures(*The input arrays `rowConditions` and `colConditions` have a size of `n` and `m` respectively.*);
//@ ensures(*Each element in the `rowConditions` array is an array of size 2, where the first element represents the number above and the second element represents the number below.*);
//@ ensures(*Each element in the `colConditions` array is an array of size 2, where the first element represents the number left and the second element represents the number right.*);
//@ ensures(*The integers in the `rowConditions` and `colConditions` arrays are between 1 and `k`, inclusive.*);
//@ ensures(*The number above and the number below in each row condition are not equal.*);
//@ ensures(*The number left and the number right in each column condition are not equal.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a 2D integer array of size `k x k`.*);
//@ ensures(*The matrix contains each number from 1 to `k` exactly once.*);
//@ ensures(*The remaining cells in the matrix have the value - The number above in each row condition appears in a row that is strictly above the row at which the number below appears.*);
//@ ensures(*The number left in each column condition appears in a column that is strictly left of the column at which the number right appears.*);
//@ ensures(*If no matrix satisfies the conditions, the method returns an empty matrix.*);
    public int[][] buildMatrix(int k, int[][] rowC, int[][] colC) {
        // First, get the topo-sorted of row and col
        List<Integer> row = toposort(k, rowC);
        List<Integer> col = toposort(k, colC);
        // base case: when the length of row or col is less than k, return empty.
        // That is: there is a loop in established graph
        if (row.size() < k || col.size() < k) {
            return new int[0][0];
        }
        int[][] res = new int[k][k];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < k; i++) {
            // we record the number corresbonding to each column:
            // [number, column index]
            map.put(col.get(i), i);
        }
        // col: 3 2 1
        // row: 1 3 2
        for (int i = 0; i < k; i++) {
            // For each row: we have number row.get(i). And we need to know
            // which column we need to assign, which is from map.get(row.get(i))
            // known by map.get()
            res[i][map.get(row.get(i))] = row.get(i);
        }
        return res;
    }

    private List<Integer> toposort(int k, int[][] matrix) {
        // need a int[] to record the indegree of each number [1, k]
        int[] deg = new int[k + 1];
        // need a list to record the order of each number, then return this list
        List<Integer> res = new ArrayList<>();
        // need a 2-D list to be the graph, and fill the graph
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            graph.add(new ArrayList<>());
        }
        // need a queue to do the BFS
        Queue<Integer> queue = new LinkedList<>();
        // First, we need to establish the graph, following the given matrix
        for (int[] a : matrix) {
            int from = a[0];
            int to = a[1];
            graph.get(from - 1).add(to);
            deg[to]++;
        }
        // Second, after building a graph, we start the bfs,
        // that is, traverse the node with 0 degree
        for (int i = 1; i <= k; i++) {
            if (deg[i] == 0) {
                queue.offer(i);
                res.add(i);
            }
        }
        // Third, start the topo sort
        while (!queue.isEmpty()) {
            int node = queue.poll();
            List<Integer> list = graph.get(node - 1);
            for (int i : list) {
                if (--deg[i] == 0) {
                    queue.offer(i);
                    res.add(i);
                }
            }
        }
        return res;
    }
}
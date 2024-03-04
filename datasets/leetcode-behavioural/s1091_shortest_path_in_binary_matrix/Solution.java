package g1001_1100.s1091_shortest_path_in_binary_matrix;

// #Medium #Array #Breadth_First_Search #Matrix
// #Algorithm_II_Day_8_Breadth_First_Search_Depth_First_Search
// #Graph_Theory_I_Day_5_Matrix_Related_Problems
// #2022_02_23_Time_22_ms_(69.99%)_Space_59.9_MB_(35.14%)

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    private int[] directions = new int[] {0, 1, 1, 0, -1, 1, -1, -1, 0};
//@ ensures(*Preconditions:*);
//@ ensures(*The input grid is a square matrix of size n x n.*);
//@ ensures(*The grid contains only binary values (0 or 1).*);
//@ ensures(*The top-left cell of the grid is (0, 0).*);
//@ ensures(*The bottom-right cell of the grid is (n - 1, n - 1).*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the length of the shortest clear path in the grid.*);
//@ ensures(*If there is no clear path, the method returns -1.*);
//@ ensures(**);
//@ ensures(*Additional behavioural requirements:*);
//@ ensures(*The method should handle the case when the grid is empty (n = 0) and return -1.*);
//@ ensures(*The method should handle the case when the grid has only one cell (n = 1) and return 1 if the cell is clear (0) or -1 if the cell is blocked (1).*);
//@ ensures(*The method should handle the case when the grid has no clear path and return -1.*);
//@ ensures(*The method should handle the case when the grid has a clear path and return the correct length of the path.*);
//@ ensures(*The method should consider all 8-directional connections when determining the clear path.*);
//@ ensures(*The method should not modify the input grid.*);

    public int shortestPathBinaryMatrix(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        if (grid[0][0] == 1 || grid[m - 1][n - 1] == 1) {
            return -1;
        }
        int minPath = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {0, 0});
        boolean[][] visited = new boolean[m][n];
        visited[0][0] = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                if (curr[0] == m - 1 && curr[1] == n - 1) {
                    return minPath + 1;
                }
                for (int j = 0; j < directions.length - 1; j++) {
                    int newx = directions[j] + curr[0];
                    int newy = directions[j + 1] + curr[1];
                    if (newx >= 0
                            && newx < n
                            && newy >= 0
                            && newy < n
                            && !visited[newx][newy]
                            && grid[newx][newy] == 0) {
                        queue.offer(new int[] {newx, newy});
                        visited[newx][newy] = true;
                    }
                }
            }
            minPath++;
        }
        return -1;
    }
}
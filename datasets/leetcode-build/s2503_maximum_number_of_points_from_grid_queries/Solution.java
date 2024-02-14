package g2501_2600.s2503_maximum_number_of_points_from_grid_queries;

// #Hard #Array #Sorting #Breadth_First_Search #Heap_Priority_Queue #Union_Find
// #2023_03_20_Time_47_ms_(96.38%)_Space_59.9_MB_(49.28%)

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {
    private final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	//@ requires(*The input matrix `grid` is a 2D integer array with dimensions `m x n`.*);
	//@ requires(*The input array `queries` is an integer array with size `k`.*);
	//@ requires(*The values in `grid` and `queries` are within the range of 1 to 10^6.*);
	//@ requires(*The dimensions of `grid` satisfy the constraints: 2 <= m, n <= 1000 and 4 <= m * n <= 10^5.*);
	//@ requires(*The size of `queries` satisfies the constraint: 1 <= k <= 10^4.*);
	//@ ensures(*The output is an integer array `answer` with size `k`.*);
	//@ ensures(*Each element in `answer` represents the maximum number of points that can be obtained for the corresponding query.*);
	//@ ensures(*The order of elements in `answer` corresponds to the order of queries in the input.*);
	//@ ensures(*The values in `answer` are within the range of 0 to the maximum number of cells in `grid` (m * n).*);

    public int[] maxPoints(int[][] grid, int[] q) {
        int r = grid.length;
        int c = grid[0].length;
        int[] res = new int[q.length];
        Integer[] index = new Integer[q.length];
        for (int i = 0; i < q.length; i++) {
            index[i] = i;
        }
        Arrays.sort(index, Comparator.comparingInt(o -> q[o]));
        Queue<int[]> q1 = new ArrayDeque<>();
        PriorityQueue<int[]> q2 = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        q2.offer(new int[] {0, 0, grid[0][0]});
        boolean[][] visited = new boolean[r][c];
        int count = 0;
        visited[0][0] = true;
        for (int i = 0; i < q.length; i++) {
            int currLimit = q[index[i]];
            while (!q2.isEmpty() && q2.peek()[2] < currLimit) {
                q1.offer(q2.poll());
            }
            while (!q1.isEmpty()) {
                int[] curr = q1.poll();
                count++;
                for (int[] dir : dirs) {
                    int x = dir[0] + curr[0];
                    int y = dir[1] + curr[1];
                    if (x < 0 || y < 0 || x >= r || y >= c || visited[x][y]) {
                        continue;
                    }
                    if (!visited[x][y] && grid[x][y] < currLimit) {
                        q1.offer(new int[] {x, y, grid[x][y]});
                    } else {
                        q2.offer(new int[] {x, y, grid[x][y]});
                    }
                    visited[x][y] = true;
                }
            }
            res[index[i]] = count;
        }
        return res;
    }
}
package g0901_1000.s0994_rotting_oranges;

// #Medium #Array #Breadth_First_Search #Matrix
// #Algorithm_I_Day_9_Breadth_First_Search_Depth_First_Search #Level_2_Day_10_Graph/BFS/DFS
// #2022_02_17_Time_3_ms_(74.27%)_Space_42.9_MB_(18.68%)

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	//@ requires(*The input grid must not be null.*);
	//@ requires(*The input grid must have at least one row and one column.*);
	//@ requires(*The values in the input grid must be either 0, 1, or 2.*);
	//@ ensures(*The method returns an integer representing the minimum number of minutes that must elapse until no cell has a fresh orange.*);
	//@ ensures(*If it is impossible for no cell to have a fresh orange, the method returns -1.*);
    public int orangesRotting(int[][] grid) {
        Queue<int[]> queue = new LinkedList<>();
        int row = grid.length;
        int col = grid[0].length;
        int countActive = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new int[] {i, j});
                }
                if (grid[i][j] != 0) {
                    countActive++;
                }
            }
        }
        if (countActive == 0) {
            return 0;
        }
        int countCurrent = 0;
        int count = 0;
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        while (!queue.isEmpty()) {
            int size = queue.size();
            count += size;
            for (int i = 0; i < size; i++) {
                int[] arr = queue.poll();
                for (int j = 0; j < 4; j++) {
                    int x = arr[0] + dx[j];
                    int y = arr[1] + dy[j];
                    if (x < 0 || y < 0 || x >= row || y >= col || grid[x][y] != 1) {
                        continue;
                    }
                    grid[x][y] = 2;
                    queue.add(new int[] {x, y});
                }
            }
            if (!queue.isEmpty()) {
                countCurrent++;
            }
        }
        return countActive == count ? countCurrent : -1;
    }
}
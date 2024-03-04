package g1901_2000.s1926_nearest_exit_from_entrance_in_maze;

// #Medium #Array #Breadth_First_Search #Matrix #Graph_Theory_I_Day_6_Matrix_Related_Problems
// #2022_05_14_Time_12_ms_(40.55%)_Space_43.3_MB_(92.19%)

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The maze is a 2D matrix with dimensions m x n.*);
//@ ensures(*The maze is 0-indexed.*);
//@ ensures(*The maze contains empty cells represented as '.' and walls represented as '+'.*);
//@ ensures(*The entrance is a valid cell in the maze, represented as [entrance_row, entrance_col].*);
//@ ensures(*The entrance cell is initially empty.*);
//@ ensures(*The entrance cell does not count as an exit.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns the number of steps in the shortest path from the entrance to the nearest exit.*);
//@ ensures(*If no path exists from the entrance to any exit, the method returns -*);
//@ ensures(*Note: The method does not modify the maze.*);
    public int nearestExit(char[][] maze, int[] entrance) {
        int m = maze.length;
        int n = maze[0].length;
        int[] directions = new int[] {0, 1, 0, -1, 0};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {entrance[0], entrance[1], 0});
        boolean[][] visited = new boolean[m][n];
        visited[entrance[0]][entrance[1]] = true;
        int shortestSteps = m * n;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int i = 0; i < directions.length - 1; i++) {
                int nextX = curr[0] + directions[i];
                int nextY = curr[1] + directions[i + 1];
                if (nextX >= 0
                        && nextX < m
                        && nextY >= 0
                        && nextY < n
                        && maze[nextX][nextY] == '.'
                        && !visited[nextX][nextY]) {
                    visited[nextX][nextY] = true;
                    if (nextX == 0 || nextX == m - 1 || nextY == 0 || nextY == n - 1) {
                        shortestSteps = Math.min(shortestSteps, curr[2] + 1);
                    } else {
                        queue.offer(new int[] {nextX, nextY, curr[2] + 1});
                    }
                }
            }
        }
        return shortestSteps == m * n ? -1 : shortestSteps;
    }
}
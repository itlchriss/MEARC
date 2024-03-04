package g1301_1400.s1368_minimum_cost_to_make_at_least_one_valid_path_in_a_grid;

// #Hard #Array #Breadth_First_Search #Matrix #Heap_Priority_Queue #Graph #Shortest_Path
// #2022_03_21_Time_8_ms_(99.38%)_Space_42.6_MB_(88.85%)

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class Solution {
    private final int[][] dir = new int[][] {{0, 0}, {0, 1}, {0, -1}, {1, 0}, {-1, 0}};
//@ ensures(*Preconditions:*);
//@ ensures(*The input grid must not be null.*);
//@ ensures(*The input grid must have at least one row and one column.*);
//@ ensures(*The values in the grid must be integers between 1 and 4.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the minimum cost to make the grid have at least one valid path.*);
//@ ensures(*The grid may be modified with a cost of 1 to change the signs on the cells.*);
//@ ensures(*The modified grid must have at least one valid path from the upper left cell to the bottom-right cell.*);
//@ ensures(*The modified grid may have multiple valid paths, and the minimum cost is the cost of the path with the fewest sign changes.*);

    public int minCost(int[][] grid) {
        int[][] visited = new int[grid.length][grid[0].length];
        Queue<Pair> queue = new LinkedList<>();
        addAllTheNodeInRange(0, 0, grid, queue, visited);
        if (visited[grid.length - 1][grid[0].length - 1] == 1) {
            return 0;
        }
        int cost = 0;
        while (!queue.isEmpty()) {
            cost++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Pair pa = queue.poll();
                for (int k = 1; k < dir.length; k++) {
                    int m = Objects.requireNonNull(pa).x + dir[k][0];
                    int n = pa.y + dir[k][1];
                    addAllTheNodeInRange(m, n, grid, queue, visited);
                    if (visited[grid.length - 1][grid[0].length - 1] == 1) {
                        return cost;
                    }
                }
            }
        }
        return -1;
    }

    private void addAllTheNodeInRange(
            int x, int y, int[][] grid, Queue<Pair> queue, int[][] visited) {
        while (x >= 0
                && x < visited.length
                && y >= 0
                && y < visited[0].length
                && visited[x][y] == 0) {
            queue.offer(new Pair(x, y));
            visited[x][y]++;
            int[] d = dir[grid[x][y]];
            x += d[0];
            y += d[1];
        }
    }

    private static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
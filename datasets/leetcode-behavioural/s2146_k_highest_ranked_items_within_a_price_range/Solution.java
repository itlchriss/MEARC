package g2101_2200.s2146_k_highest_ranked_items_within_a_price_range;

// #Medium #Array #Sorting #Breadth_First_Search #Matrix #Heap_Priority_Queue
// #2022_06_07_Time_81_ms_(88.84%)_Space_65.7_MB_(99.30%)

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
    static class Item {
        int row;
        int col;
        int dist;
        int price;
//@ ensures(*Preconditions:*);
//@ ensures(*The grid is a 2D integer array of size m x n.*);
//@ ensures(*The grid is 0-indexed.*);
//@ ensures(*The integers in the grid represent walls, empty cells, or the price of an item.*);
//@ ensures(*The pricing array contains two integers representing the low and high prices.*);
//@ ensures(*The start array contains two integers representing the starting position.*);
//@ ensures(*The k integer represents the number of highest-ranked items to find.*);
//@ ensures(*The price range is inclusive.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a 2D integer array containing the positions of the k highest-ranked items within the price range, sorted by their rank.*);
//@ ensures(*If there are fewer than k reachable items within the price range, the method returns all of them.*);

        public Item(int row, int col, int dist, int price) {
            this.row = row;
            this.col = col;
            this.dist = dist;
            this.price = price;
        }
    }

    public List<List<Integer>> highestRankedKItems(
            int[][] grid, int[] pricing, int[] start, int k) {
        int n = grid.length;
        int m = grid[0].length;
        Queue<int[]> bfs = new LinkedList<>();
        LinkedList<Item> items = new LinkedList<>();

        bfs.add(start);
        if (grid[start[0]][start[1]] >= pricing[0] && grid[start[0]][start[1]] <= pricing[1]) {
            items.add(new Item(start[0], start[1], 0, grid[start[0]][start[1]]));
        }
        grid[start[0]][start[1]] = -1;

        int distance = 0;
        while (!bfs.isEmpty()) {
            int size = bfs.size();
            distance++;
            while (size-- > 0) {
                int[] loc = bfs.poll();
                int[] dirX = {0, 1, -1, 0};
                int[] dirY = {-1, 0, 0, 1};
                for (int i = 0; i < 4; i++) {
                    int newX = loc[0] + dirX[i];
                    int newY = loc[1] + dirY[i];
                    if (newX < 0
                            || newX >= n
                            || newY < 0
                            || newY >= m
                            || grid[newX][newY] == -1
                            || grid[newX][newY] == 0) {
                        continue;
                    }
                    if (grid[newX][newY] >= pricing[0] && grid[newX][newY] <= pricing[1]) {
                        items.add(new Item(newX, newY, distance, grid[newX][newY]));
                    }
                    grid[newX][newY] = -1;
                    bfs.add(new int[] {newX, newY});
                }
            }
        }
        items.sort(
                (a, b) -> {
                    int distDiff = a.dist - b.dist;
                    if (distDiff == 0) {
                        int priceDiff = a.price - b.price;
                        if (priceDiff == 0) {
                            int rowDiff = a.row - b.row;
                            if (rowDiff == 0) {
                                return a.col - b.col;
                            }
                            return rowDiff;
                        }
                        return priceDiff;
                    }
                    return distDiff;
                });
        List<List<Integer>> ans = new LinkedList<>();
        while (k-- > 0 && !items.isEmpty()) {
            Item item = items.poll();
            ans.add(Arrays.asList(item.row, item.col));
        }
        return ans;
    }
}
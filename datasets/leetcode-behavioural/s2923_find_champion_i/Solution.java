package g2901_3000.s2923_find_champion_i;

// #Easy #Array #Matrix #2023_12_29_Time_1_ms_(96.00%)_Space_45.2_MB_(6.05%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input grid must be a 2D boolean matrix of size n * n.*);
//@ ensures(*The length of the grid must be equal to the length of each row in the grid.*);
//@ ensures(*The value of n must be between 2 and 100 (inclusive).*);
//@ ensures(*Each element in the grid must be either 0 or 1.*);
//@ ensures(*The diagonal elements of the grid must be 0.*);
//@ ensures(*The grid must satisfy the transitive property, meaning if team a is stronger than team b and team b is stronger than team c, then team a is stronger than team c.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method should return the team that will be the champion of the tournament.*);
    public int findChampion(int[][] grid) {
        int champion = grid[1][0];
        for (int opponent = 2; opponent < grid.length; opponent++) {
            if (grid[opponent][champion] != 0) {
                champion = opponent;
            }
        }
        return champion;
    }
}
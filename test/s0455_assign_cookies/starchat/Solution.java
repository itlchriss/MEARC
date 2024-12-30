package g0401_0500.s0455_assign_cookies;

// #Easy #Array #Sorting #Greedy #2022_07_18_Time_12_ms_(41.00%)_Space_52.6_MB_(78.45%)

import java.util.Arrays;

public class Solution {
//@ requires(*The length of the integer array parameter `g` is less than or equal to 30000 and is greater than or equal to 1.*);
//@ requires(*The length of the integer array parameter `s` is less than or equal to 30000 and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `g` are less than or equal to 231 - 1 and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `s` are less than or equal to 231 - 1 and is greater than or equal to 1.*);
//@ requires(**);
//@ requires(*Given the following context,*);
//@ requires(*Software specification: *);
//@ requires(*135\. Candy Crush*);
//@ requires(**);
//@ requires(*Hard*);
//@ requires(**);
//@ requires(*This question is about implementing the logic of candy crush.*);
//@ requires(**);
//@ requires(*Given a 2D integer array `board` representing the grid of candy, different positive integers `board[i][j]` represent different types of candies. A value of `board[i][j] == 0` represents that the cell at position `(i, j)` is empty. The given board represents the state of the game following the player's move. Now, you need to implement the `candyCrush` function that returns the new state of the game board after the player's move.*);
//@ requires(**);
//@ requires(*The rules of candy crush are:*);
//@ requires(**);
//@ requires(**   From left to right, each row must be treated separately.*);
//@ requires(**   Two candies of the same type are **not** grouped in this row.*);
//@ requires(**   A candy can crush the left, right, or both adjacent candies of the same type.*);
//@ requires(**   After crushing all candies simultaneously, if an empty space on the board has candies on top of itself, then these candies will drop until they hit a candy or bottom at the same time. (No new candies will drop outside the top boundary.)*);
//@ requires(**   After the above steps, there may exist more candies that can be crushed. If so, you need to repeat the above steps.*);
//@ requires(**   If there does not exist more candies that can be crushed (ie. the board becomes empty), then the game ends.*);
//@ requires(**);
//@ requires(***Example:***);
//@ requires(**);
//@ requires(***Input:***);
//@ requires(*board = *);
//@ requires(*[[110,5,112,113,114],[210,211,5,213,214],[310,311,3,313,314],[410,411,412,5,414],[5,1,512,3,3],[610,4,1,613,614],[710,1,2,713,714],[810,1,2,1,1],[1,1,2,2,2],[4,1,4,4,1014]]*);
//@ requires(**);
//@ requires(***Output:***);
//@ requires(*[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[110,0,0,0,114],[210,0,0,0,214],[310,0,0,113,314],[410,0,0,213,414],[610,211,112,313,614],[710,311,512,413,714],[810,411,512,513,1014]]*);
//@ requires(**);
//@ requires(***Explanation:** *);
//@ requires(*The above figure represents the initial board.*);
//@ requires(**);
//@ requires(*After candy crush, the board becomes:*);
//@ requires(**);
//@ requires(*The above figure represents the board after candy crush.*);
//@ requires(**);
//@ requires(*Note that the board on the right side is the final board after the game.*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= board.length <= 200`*);
//@ requires(**   `1 <= board[0].length <= 200`*);
//@ ensures(*The integer result is greater than or equal to 0 and is less than or equal to the length of the integer array parameter `g`.*);
//@ ensures(*If the integer array parameter `g` is equal to [1,2,3] and the integer array parameter `s` is equal to [1,1], the integer result is equal to 1.*);
//@ ensures(*If the integer array parameter `g` is equal to [1,2] and the integer array parameter `s` is equal to [1,2,3], the integer result is equal to 2.*);
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int result = 0;
        int i = 0;
        int j = 0;
        //@ maintaining 0 <= i <= g.length;
        //@ maintaining 0 <= j <= s.length;
        while (i < g.length && j < s.length) {
            if (s[j] >= g[i]) {
                result++;
                i++;
            }
            j++;
        }
        return result;
    }
}
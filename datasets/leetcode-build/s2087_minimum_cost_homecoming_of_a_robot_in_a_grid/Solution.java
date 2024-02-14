package g2001_2100.s2087_minimum_cost_homecoming_of_a_robot_in_a_grid;

// #Medium #Array #Greedy #Matrix #2022_05_27_Time_2_ms_(79.89%)_Space_61.9_MB_(83.07%)

public class Solution {
	//@ requires(*The input arrays `startPos`, `homePos`, `rowCosts`, and `colCosts` are not null.*);
	//@ requires(*The length of `rowCosts` is equal to `m`.*);
	//@ requires(*The length of `colCosts` is equal to `n`.*);
	//@ requires(*The values in `rowCosts` and `colCosts` are non-negative integers.*);
	//@ requires(*The values in `startPos` and `homePos` are valid cell coordinates within the grid.*);
	//@ ensures(*The method returns an integer representing the minimum total cost for the robot to return home.*);
	//@ ensures(*The robot's final position is equal to `homePos`.*);
	//@ ensures(*The robot has moved from `startPos` to `homePos` using the minimum cost path.*);
	//@ ensures(*The total cost is calculated by summing the costs of each move made by the robot.*);
	//@ ensures(*The robot can only move one cell at a time in four directions: left, right, up, or down.*);
	//@ ensures(*The robot cannot move outside the boundary of the grid.*);
	//@ ensures(*The cost of moving up or down into a cell is determined by the corresponding value in `rowCosts`.*);
	//@ ensures(*The cost of moving left or right into a cell is determined by the corresponding value in `colCosts`.*);
    public int minCost(int[] startPos, int[] homePos, int[] rowCosts, int[] colCosts) {
        int cost = 0;
        for (int i = Math.min(startPos[0], homePos[0]);
                i <= Math.max(startPos[0], homePos[0]);
                i++) {
            cost += rowCosts[i];
        }
        for (int j = Math.min(startPos[1], homePos[1]);
                j <= Math.max(startPos[1], homePos[1]);
                j++) {
            cost += colCosts[j];
        }
        return cost - rowCosts[startPos[0]] - colCosts[startPos[1]];
    }
}
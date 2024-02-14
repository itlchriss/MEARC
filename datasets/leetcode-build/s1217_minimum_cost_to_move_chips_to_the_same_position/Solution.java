package g1201_1300.s1217_minimum_cost_to_move_chips_to_the_same_position;

// #Easy #Array #Math #Greedy #2022_03_09_Time_0_ms_(100.00%)_Space_41.8_MB_(27.65%)

public class Solution {
	//@ requires(*The input array `position` is not null.*);
	//@ requires(*The length of the input array `position` is greater than or equal to 1.*);
	//@ requires(*Each element in the input array `position` is a positive integer.*);
	//@ ensures(*The output is an integer representing the minimum cost needed to move all the chips to the same position.*);
    public int minCostToMoveChips(int[] position) {
        int chipsAtOddPosition = 0;
        int chipsAtEvenPosition = 0;
        for (int j : position) {
            if (j % 2 == 0) {
                chipsAtEvenPosition++;
            } else {
                chipsAtOddPosition++;
            }
        }
        return Math.min(chipsAtEvenPosition, chipsAtOddPosition);
    }
}
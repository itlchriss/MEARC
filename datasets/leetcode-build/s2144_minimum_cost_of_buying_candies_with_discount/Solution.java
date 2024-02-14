package g2101_2200.s2144_minimum_cost_of_buying_candies_with_discount;

// #Easy #Array #Sorting #Greedy #2022_06_07_Time_2_ms_(97.50%)_Space_41.4_MB_(99.04%)

import java.util.Arrays;

public class Solution {
	//@ requires(*The input array `cost` is not null.*);
	//@ requires(*The length of the input array `cost` is greater than or equal to - Each element in the input array `cost` is an integer.*);
	//@ requires(*Each element in the input array `cost` is greater than or equal to - Each element in the input array `cost` is less than or equal to*);
	//@ ensures(*The return value is an integer.*);
	//@ ensures(*The return value is the minimum cost of buying all the candies.*);
    public int minimumCost(int[] cost) {
        Arrays.sort(cost);
        int size = 0;
        int sum = 0;
        for (int i = cost.length - 1; i >= 0; i--) {
            size++;
            if (size % 3 != 0) {
                sum += cost[i];
            }
        }

        return sum;
    }
}
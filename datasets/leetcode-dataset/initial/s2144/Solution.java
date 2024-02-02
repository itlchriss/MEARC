package g2101_2200.s2144_minimum_cost_of_buying_candies_with_discount;

// #Easy #Array #Sorting #Greedy #2022_06_07_Time_2_ms_(97.50%)_Space_41.4_MB_(99.04%)

import java.util.Arrays;

public class Solution {
<<<<<<< HEAD
=======
//@ ensures(*Given a **0-indexed** integer array `cost`, where `cost[i]` denotes the cost of the <code>i<sup>th</sup></code> candy, return _the **minimum cost** of buying **all** the candies_.*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
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
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03

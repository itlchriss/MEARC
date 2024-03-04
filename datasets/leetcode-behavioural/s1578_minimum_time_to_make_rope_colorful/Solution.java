package g1501_1600.s1578_minimum_time_to_make_rope_colorful;

// #Medium #Array #String #Dynamic_Programming #Greedy
// #2022_04_11_Time_4_ms_(100.00%)_Space_50.9_MB_(91.79%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `colors` is not null.*);
//@ ensures(*The input array `neededTime` is not null.*);
//@ ensures(*The length of `colors` is equal to the length of `neededTime`.*);
//@ ensures(*The length of `colors` is greater than 1.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is an integer representing the minimum time Bob needs to make the rope colorful.*);
//@ ensures(*The output is non-negative.*);
//@ ensures(*The output is the minimum time among all possible ways to remove balloons to make the rope colorful.*);
    public int minCost(String s, int[] cost) {
        char[] str = s.toCharArray();
        int minCost = 0;
        for (int i = 1; i < str.length; i++) {
            if (str[i] == str[i - 1]) {
                // accrue the cost of deletion for the lower duplicate
                minCost += Math.min(cost[i], cost[i - 1]);
                // keep the cost of the higher duplicate for next iteration
                cost[i] = Math.max(cost[i], cost[i - 1]);
            }
        }
        return minCost;
    }
}
package g1001_1100.s1029_two_city_scheduling;

// #Medium #Array #Sorting #Greedy #2022_02_27_Time_1_ms_(97.54%)_Space_39.9_MB_(53.65%)

import java.util.Arrays;

public class Solution {
	//@ requires(*The input array `costs` is not null.*);
	//@ requires(*The length of the input array `costs` is even.*);
	//@ requires(*The length of the input array `costs` is at least 2 and at most 100.*);
	//@ requires(*The values of `aCosti` and `bCosti` in each element of the input array `costs` are between 1 and 1000 (inclusive).*);
	//@ ensures(*The method returns an integer representing the minimum cost to fly every person to a city.*);
	//@ ensures(*Exactly `n` people arrive in each city, where `n` is half the length of the input array `costs`.*);
	//@ ensures(*The total minimum cost is calculated by summing the costs of flying each person to their respective cities.*);
    public int twoCitySchedCost(int[][] costs) {
        Arrays.sort(costs, (a, b) -> (a[0] - a[1] - (b[0] - b[1])));
        int cost = 0;
        for (int i = 0; i < costs.length; i++) {
            if (i < costs.length / 2) {
                cost += costs[i][0];
            } else {
                cost += costs[i][1];
            }
        }
        return cost;
    }
}
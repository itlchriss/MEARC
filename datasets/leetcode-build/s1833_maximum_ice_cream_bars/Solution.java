package g1801_1900.s1833_maximum_ice_cream_bars;

// #Medium #Array #Sorting #Greedy #2022_05_07_Time_39_ms_(84.49%)_Space_56.7_MB_(78.12%)

import java.util.Arrays;

public class Solution {
	//@ requires(*The input array `costs` is not null.*);
	//@ requires(*The input array `costs` has at least one element.*);
	//@ requires(*The input array `costs` contains positive integers.*);
	//@ requires(*The input integer `coins` is positive.*);
	//@ ensures(*The output is an integer.*);
	//@ ensures(*The output is greater than or equal to 0.*);
	//@ ensures(*The output is less than or equal to the length of the input array `costs`.*);
	//@ ensures(*The output represents the maximum number of ice cream bars that can be bought with the given number of coins.*);
    public int maxIceCream(int[] costs, int coins) {
        Arrays.sort(costs);
        int i = 0;
        int ans = 0;
        while (i < costs.length && costs[i] <= coins) {
            coins -= costs[i];
            i++;
            ans++;
        }
        return ans;
    }
}
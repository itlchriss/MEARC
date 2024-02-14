package g2901_3000.s2952_minimum_number_of_coins_to_be_added;

// #Medium #Array #Sorting #Greedy #2024_01_15_Time_21_ms_(98.49%)_Space_61.9_MB_(5.41%)

import java.util.Arrays;

public class Solution {
	//@ requires(*The input array `coins` is not null.*);
	//@ requires(*The input array `coins` is not empty.*);
	//@ requires(*The input array `coins` is a 0-indexed integer array.*);
	//@ requires(*The input integer `target` is greater than or equal to 1.*);
	//@ requires(*The input integer `target` is less than or equal to 10^5.*);
	//@ requires(*The length of the input array `coins` is greater than or equal to 1.*);
	//@ requires(*The length of the input array `coins` is less than or equal to 10^5.*);
	//@ requires(*Each element in the input array `coins` is greater than or equal to 1.*);
	//@ requires(*Each element in the input array `coins` is less than or equal to the input integer `target`.*);
	//@ ensures(*The method returns an integer representing the minimum number of coins that need to be added to the array.*);
	//@ ensures(*The resulting array after adding the minimum number of coins contains all integers from 1 to the input integer `target`.*);
	//@ ensures(*The resulting array after adding the minimum number of coins is sorted in ascending order.*);
    public int minimumAddedCoins(int[] coins, int target) {
        int res = 0;
        int num = 0;
        int i = 0;
        Arrays.sort(coins);
        while (num < target) {
            if (i < coins.length && coins[i] <= num + 1) {
                num += coins[i];
                i++;
            } else {
                res += 1;
                num += num + 1;
            }
        }
        return res;
    }
}
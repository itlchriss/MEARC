package g1601_1700.s1655_distribute_repeating_integers;

// #Hard #Array #Dynamic_Programming #Bit_Manipulation #Backtracking #Bitmask
// #2022_04_23_Time_7_ms_(94.23%)_Space_75.4_MB_(73.08%)

import java.util.Arrays;

public class Solution {
	//@ requires(*The input arrays `nums` and `quantity` are not null.*);
	//@ requires(*The length of `nums` is equal to `n`.*);
	//@ requires(*The length of `quantity` is equal to `m`.*);
	//@ requires(*The values in `nums` are integers between 1 and 1000.*);
	//@ requires(*The values in `quantity` are integers between 1 and 100000.*);
	//@ requires(*The number of unique values in `nums` is at most 50.*);
	//@ ensures(*The method returns a boolean value indicating whether it is possible to distribute `nums` according to the given conditions.*);
	//@ ensures(*If the method returns `true`, it means that it is possible to distribute `nums` and satisfy all customers.*);
	//@ ensures(*If the method returns `false`, it means that it is not possible to distribute `nums` and satisfy all customers.*);
	//@ ensures(*The distribution of `nums` should ensure that each customer gets exactly the quantity of integers specified in `quantity`.*);
	//@ ensures(*The integers received by each customer should be equal.*);
	//@ ensures(*The distribution should satisfy all customers, meaning that every customer should receive the specified quantity of integers.*);
    public boolean canDistribute(int[] nums, int[] quantity) {
        int[] counter = count(nums);
        Arrays.sort(quantity);
        return dfs(counter, quantity, quantity.length - 1);
    }

    private boolean dfs(int[] counter, int[] quantity, int quantityId) {
        if (quantityId < 0) {
            return true;
        }
        for (int i = 0; i < counter.length; i++) {
            if (i > 0 && counter[i] == counter[i - 1]) {
                continue;
            }
            if (counter[i] >= quantity[quantityId]) {
                counter[i] -= quantity[quantityId];
                if (dfs(counter, quantity, quantityId - 1)) {
                    return true;
                }
                counter[i] += quantity[quantityId];
            }
        }
        return false;
    }

    private int[] count(int[] nums) {
        int[] counter = new int[1001];
        for (int n : nums) {
            counter[n]++;
        }
        Arrays.sort(counter);
        return Arrays.copyOfRange(counter, counter.length - 50, counter.length);
    }
}
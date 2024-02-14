package g2401_2500.s2448_minimum_cost_to_make_array_equal;

// #Hard #Array #Sorting #Binary_Search #Prefix_Sum
// #2022_12_14_Time_19_ms_(92.19%)_Space_56_MB_(90.89%)

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solution {
    private static class Pair {
        int e;
        int c;

        Pair(int e, int c) {
            this.e = e;
            this.c = c;
        }
    }
	//@ requires(*The length of `nums` and `cost` arrays must be the same.*);
	//@ requires(*The length of `nums` and `cost` arrays must be greater than or equal to 1.*);
	//@ requires(*All elements in `nums` and `cost` arrays must be positive integers.*);
	//@ requires(*The maximum value of `n` is 10^5.*);
	//@ requires(*The maximum value of each element in `nums` and `cost` arrays is 10^6.*);
	//@ ensures(*The return value is a long integer representing the minimum total cost.*);
	//@ ensures(*All elements in the `nums` array are equal.*);

    public long minCost(int[] nums, int[] cost) {
        long sum = 0;
        List<Pair> al = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            al.add(new Pair(nums[i], cost[i]));
            sum += cost[i];
        }
        al.sort(Comparator.comparingInt(a -> a.e));
        long ans = 0;
        long mid = (sum + 1) / 2;
        long s2 = 0;
        int t = 0;
        for (int i = 0; i < al.size() && s2 < mid; i++) {
            s2 += al.get(i).c;
            t = al.get(i).e;
        }
        for (int i = 0; i < al.size(); i++) {
            ans += Math.abs((long) nums[i] - (long) t) * cost[i];
        }
        return ans;
    }
}
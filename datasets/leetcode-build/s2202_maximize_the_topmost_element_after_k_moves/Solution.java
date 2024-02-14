package g2201_2300.s2202_maximize_the_topmost_element_after_k_moves;

// #Medium #Array #Greedy #2022_06_10_Time_1_ms_(100.00%)_Space_84.7_MB_(16.00%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to 1.*);
	//@ requires(*The input integer `k` is greater than or equal to 0.*);
	//@ ensures(*The return value is an integer representing the maximum value of the topmost element of the pile after exactly `k` moves.*);
	//@ ensures(*If it is not possible to obtain a non-empty pile after `k` moves, the return value is -1.*);
    public int maximumTop(int[] nums, int k) {
        int max = -1;
        int maxTravers = Math.min(k + 1, nums.length);
        if (nums.length == 1) {
            if (k % 2 == 0) {
                return nums[0];
            } else {
                return max;
            }
        }
        for (int i = 0; i < maxTravers; i++) {
            if (nums[i] > max && i != k - 1) {
                max = nums[i];
            }
        }
        return max;
    }
}
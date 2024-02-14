package g2101_2200.s2195_append_k_integers_with_minimal_sum;

// #Medium #Array #Math #Sorting #Greedy #2022_06_06_Time_19_ms_(96.88%)_Space_60_MB_(78.44%)

import java.util.Arrays;

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of `nums` is greater than or equal to 1 and less than or equal to 10^5.*);
	//@ requires(*Each element in `nums` is a positive integer greater than or equal to 1 and less than or equal to 10^9.*);
	//@ requires(*The value of `k` is a positive integer greater than or equal to 1 and less than or equal to 10^8.*);
	//@ ensures(*The sum of the elements in the modified `nums` array is minimized.*);
	//@ ensures(*The modified `nums` array contains `k` unique positive integers that do not appear in the original `nums` array.*);
	//@ ensures(*The return value is the sum of the `k` integers appended to `nums`.*);
    public long minimalKSum(int[] nums, int k) {
        Arrays.sort(nums);
        long sum = 0;
        int n = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                if (nums[i] - n > k) {
                    break;
                }
                sum += nums[i];
                n++;
            }
        }
        long t = n + (long) k;
        return (1 + t) * t / 2 - sum;
    }
}
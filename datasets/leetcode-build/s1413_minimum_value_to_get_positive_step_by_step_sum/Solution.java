package g1401_1500.s1413_minimum_value_to_get_positive_step_by_step_sum;

// #Easy #Array #Prefix_Sum #2022_03_26_Time_0_ms_(100.00%)_Space_41.7_MB_(40.67%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to 1.*);
	//@ requires(*Each element in the input array `nums` is an integer.*);
	//@ requires(*The range of each element in the input array `nums` is between -100 and 100.*);
	//@ ensures(*The output is an integer.*);
	//@ ensures(*The output is the minimum positive value of `startValue` such that the step by step sum is never less than 1.*);
    public int minStartValue(int[] nums) {
        int min = Integer.MAX_VALUE;
        int sum = 0;
        for (int num : nums) {
            sum += num;
            min = Math.min(sum, min);
        }
        return min > 0 ? 1 : Math.abs(min) + 1;
    }
}
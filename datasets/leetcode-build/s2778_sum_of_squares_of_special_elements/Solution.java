package g2701_2800.s2778_sum_of_squares_of_special_elements;

// #Easy #Array #Simulation #2023_09_21_Time_1_ms_(100.00%)_Space_42.8_MB_(94.54%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to 1.*);
	//@ requires(*The length of the input array `nums` is equal to `n`.*);
	//@ requires(*Each element in the input array `nums` is an integer.*);
	//@ requires(*Each element in the input array `nums` is greater than or equal to 1.*);
	//@ requires(*Each element in the input array `nums` is less than or equal to 50.*);
	//@ ensures(*The return value is an integer.*);
	//@ ensures(*The return value is the sum of the squares of all special elements in the input array `nums`.*);
    public int sumOfSquares(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums.length % (i + 1) == 0) {
                sum += nums[i] * nums[i];
            }
        }
        return sum;
    }
}
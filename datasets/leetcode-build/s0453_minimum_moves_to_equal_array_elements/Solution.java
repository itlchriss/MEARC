package g0401_0500.s0453_minimum_moves_to_equal_array_elements;

// #Medium #Array #Math #2022_07_18_Time_2_ms_(89.66%)_Space_54.4_MB_(32.48%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to - The length of the input array `nums` is less than or equal to 10^- The elements in the input array `nums` are integers.*);
	//@ requires(*The elements in the input array `nums` are within the range of -10^9 to 10^*);
	//@ ensures(*The method returns an integer representing the minimum number of moves required to make all elements in the array equal.*);
	//@ ensures(*The answer is guaranteed to fit in a 32-bit integer.*);
    public int minMoves(int[] nums) {
        int min = nums[0];
        int sum = nums[0];
        // determining the total sum and smallest element of the input array
        for (int i = 1; i <= nums.length - 1; i++) {
            sum += nums[i];
            min = Math.min(min, nums[i]);
        }
        return sum - (min * nums.length);
    }
}
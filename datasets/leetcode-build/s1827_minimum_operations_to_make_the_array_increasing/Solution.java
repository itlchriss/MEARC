package g1801_1900.s1827_minimum_operations_to_make_the_array_increasing;

// #Easy #Array #Greedy #2022_05_06_Time_4_ms_(35.67%)_Space_49.5_MB_(20.66%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to 1.*);
	//@ requires(*The elements in the input array `nums` are positive integers.*);
	//@ ensures(*The output is an integer representing the minimum number of operations needed to make the array `nums` strictly increasing.*);
	//@ ensures(*The input array `nums` remains unchanged.*);
	//@ ensures(*The elements in the input array `nums` are not modified.*);
    public int minOperations(int[] nums) {
        int minsOps = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] <= nums[i - 1]) {
                minsOps += nums[i - 1] - nums[i] + 1;
                nums[i] = nums[i - 1] + 1;
            }
        }
        return minsOps;
    }
}
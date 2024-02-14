package g2901_3000.s2996_smallest_missing_integer_greater_than_sequential_prefix_sum;

// #Easy #Array #Hash_Table #Sorting #2024_01_17_Time_1_ms_(93.13%)_Space_42.2_MB_(58.31%)

import java.util.Arrays;

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to 1.*);
	//@ requires(*Each element in the input array `nums` is an integer.*);
	//@ requires(*Each element in the input array `nums` is greater than or equal to 1.*);
	//@ requires(*Each element in the input array `nums` is less than or equal to 50.*);
	//@ ensures(*The output is an integer.*);
	//@ ensures(*The output is greater than or equal to the sum of the longest sequential prefix of the input array `nums`.*);
	//@ ensures(*The output is the smallest missing integer greater than or equal to the sum of the longest sequential prefix of the input array `nums`.*);
    public int missingInteger(int[] nums) {
        int n = nums.length;
        int sum = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                sum = sum + nums[i];
            } else {
                break;
            }
        }
        Arrays.sort(nums);
        for (int no : nums) {
            if (no == sum) {
                sum++;
            }
        }
        return sum;
    }
}
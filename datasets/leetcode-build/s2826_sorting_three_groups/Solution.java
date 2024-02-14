package g2801_2900.s2826_sorting_three_groups;

// #Medium #Array #Dynamic_Programming #2023_12_11_Time_4_ms_(100.00%)_Space_43.5_MB_(79.36%)

import java.util.List;

public class Solution {
	//@ requires(*The input list `nums` is not null.*);
	//@ requires(*The length of the input list `nums` is greater than or equal to 1.*);
	//@ requires(*Each element in the input list `nums` is an integer.*);
	//@ requires(*Each element in the input list `nums` is between 1 and 3 (inclusive).*);
	//@ ensures(*The output is an integer representing the minimum number of operations required to make the input list `nums` a beautiful array.*);
	//@ ensures(*The input list `nums` remains unchanged.*);
	//@ ensures(*The output is non-negative.*);
    public int minimumOperations(List<Integer> nums) {
        int n = nums.size();
        int[] arr = new int[3];
        int max = 0;
        for (Integer num : nums) {
            int locMax = 0;
            int value = num;
            for (int j = 0; j < value; j++) {
                locMax = Math.max(locMax, arr[j]);
            }

            locMax++;
            arr[value - 1] = locMax;
            if (locMax > max) {
                max = locMax;
            }
        }
        return n - max;
    }
}
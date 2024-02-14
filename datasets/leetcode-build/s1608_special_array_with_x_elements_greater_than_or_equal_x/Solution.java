package g1601_1700.s1608_special_array_with_x_elements_greater_than_or_equal_x;

// #Easy #Array #Sorting #Binary_Search #Binary_Search_I_Day_7
// #2022_04_13_Time_2_ms_(61.14%)_Space_40.5_MB_(81.84%)

import java.util.Arrays;

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is between 1 and 100 (inclusive).*);
	//@ requires(*Each element in the input array `nums` is a non-negative integer between 0 and 1000 (inclusive).*);
	//@ ensures(*If there exists a number `x` such that there are exactly `x` numbers in `nums` that are greater than or equal to `x`, return `x`.*);
	//@ ensures(*If there is no such number `x`, return -- The value of `x` returned is unique.*);
    public int specialArray(int[] nums) {
        Arrays.sort(nums);
        int max = nums[nums.length - 1];
        for (int x = 1; x <= max; x++) {
            int found = 0;
            int i = nums.length - 1;
            while (i >= 0 && nums[i] >= x) {
                i--;
                found++;
            }
            if (found == x) {
                return x;
            }
        }
        return -1;
    }
}
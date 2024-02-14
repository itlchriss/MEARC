package g0101_0200.s0164_maximum_gap;

// #Hard #Array #Sorting #Bucket_Sort #Radix_Sort
// #2022_06_25_Time_48_ms_(53.59%)_Space_84.1_MB_(20.66%)

import java.util.Arrays;

public class Solution {
	//@ requires(*1. The input array `nums` must not be null.*);
	//@ requires(*2. The length of the input array `nums` must be greater than or equal to 2.*);
	//@ ensures(*1. The method returns an integer value representing the maximum difference between two successive elements in the sorted form of the input array `nums`.*);
	//@ ensures(*2. If the input array `nums` contains less than two elements, the method returns 0.*);
	//@ ensures(*3. The algorithm used by the method runs in linear time.*);
	//@ ensures(*4. The algorithm used by the method uses linear extra space.*);
	//@ ensures(*5. The input array `nums` remains unchanged after the method is executed.*);
    public int maximumGap(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }
        int ret = Integer.MIN_VALUE;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if ((nums[i + 1] - nums[i]) > ret) {
                ret = (nums[i + 1] - nums[i]);
            }
        }
        return ret;
    }
}
package g1901_2000.s1968_array_with_elements_not_equal_to_average_of_neighbors;

// #Medium #Array #Sorting #Greedy #2022_05_21_Time_6_ms_(92.86%)_Space_64.9_MB_(77.92%)

import java.security.SecureRandom;
import java.util.Random;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is at least 3.*);
//@ ensures(*The elements in the input array `nums` are distinct integers.*);
//@ ensures(*The elements in the input array `nums` are non-negative integers.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output array is a rearrangement of the input array `nums`.*);
//@ ensures(*For every index `i` in the range `1 <= i < nums.length - 1`, the average of `nums[i-1]` and `nums[i+1]` is not equal to `nums[i]`.*);
//@ ensures(*The length of the output array is the same as the length of the input array `nums`.*);
//@ ensures(*The elements in the output array are distinct integers.*);
//@ ensures(*The elements in the output array are non-negative integers.*);
    public int[] rearrangeArray(int[] nums) {
        Random random = new SecureRandom();
        while (true) {
            int i;
            for (i = 1; i < nums.length - 1; i++) {
                if (2 * nums[i] == nums[i - 1] + nums[i + 1]) {
                    break;
                }
            }
            if (i == nums.length - 1) {
                return nums;
            }
            for (i = 0; i < nums.length; i++) {
                int j = i + random.nextInt(nums.length - i);
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
            }
        }
    }
}
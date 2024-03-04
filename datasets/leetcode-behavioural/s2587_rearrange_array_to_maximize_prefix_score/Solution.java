package g2501_2600.s2587_rearrange_array_to_maximize_prefix_score;

// #Medium #Array #Sorting #Greedy #Prefix_Sum
// #2023_08_22_Time_28_ms_(92.55%)_Space_57.7_MB_(77.66%)

import java.util.Arrays;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to - The elements in the input array `nums` are integers.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer value representing the maximum score.*);
//@ ensures(*The maximum score is the number of positive integers in the array `prefix`.*);
//@ ensures(*The array `prefix` is obtained by rearranging the elements of `nums`.*);
//@ ensures(*The array `prefix` contains the prefix sums of `nums` after rearranging it.*);
//@ ensures(*The elements in the array `prefix` are the sum of the elements from index 0 to i in `nums` after rearranging it.*);
//@ ensures(*The maximum score is the maximum number of positive integers in the array `prefix` that can be obtained by rearranging the elements of `nums`.*);
    public int maxScore(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        long sum = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            sum += nums[i];
            if (sum > 0) {
                count++;
            }
        }
        return count;
    }
}
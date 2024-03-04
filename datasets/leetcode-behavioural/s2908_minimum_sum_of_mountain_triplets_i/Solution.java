package g2901_3000.s2908_minimum_sum_of_mountain_triplets_i;

// #Easy #Array #2023_12_27_Time_1_ms_(99.90%)_Space_42.2_MB_(5.53%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` must not be null.*);
//@ ensures(*The length of the input array `nums` must be at least 3.*);
//@ ensures(*Each element in the input array `nums` must be an integer.*);
//@ ensures(*Each element in the input array `nums` must be between 1 and 50 (inclusive).*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the minimum possible sum of a mountain triplet in the input array `nums`.*);
//@ ensures(*If no mountain triplet exists in the input array `nums`, the method returns -1.*);
    public int minimumSum(int[] nums) {
        int output = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                if (nums[i] > nums[j]) {
                    break;
                }
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] < nums[j] && nums[k] < nums[j]) {
                        int min = nums[i] + nums[k] + nums[j];
                        output = Math.min(min, output);
                    }
                }
            }
        }
        return output == Integer.MAX_VALUE ? -1 : output;
    }
}
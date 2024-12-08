package g2901_3000.s2903_find_indices_with_index_and_value_difference_i;

// #Easy #Array #2023_12_26_Time_1_ms_(99.89%)_Space_43.4_MB_(77.84%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` must not be null.*);
//@ ensures(*The length of the input array `nums` must be greater than or equal to - The value of `indexDifference` must be greater than or equal to - The value of `valueDifference` must be greater than or equal to *);
//@ ensures(*Postconditions:*);
//@ ensures(*If there are two indices `i` and `j` that satisfy the conditions `abs(i - j) >= indexDifference` and `abs(nums[i] - nums[j]) >= valueDifference`, then the method should return an integer array `answer` containing the values of `i` and `j`.*);
//@ ensures(*If there are multiple choices for the two indices, the method can return any of them.*);
//@ ensures(*If there are no two indices that satisfy the conditions, the method should return the integer array `[-1, -1]`.*);
    public int[] findIndices(int[] nums, int indexDifference, int valueDifference) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (j - i >= indexDifference && Math.abs(nums[i] - nums[j]) >= valueDifference) {
                    return new int[] {i, j};
                }
            }
        }
        return new int[] {-1, -1};
    }
}
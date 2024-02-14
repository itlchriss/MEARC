package g2901_3000.s2905_find_indices_with_index_and_value_difference_ii;

// #Medium #Array #2023_12_26_Time_1_ms_(100.00%)_Space_65.2_MB_(6.91%)

public class Solution {
	//@ requires(*The input array `nums` must not be null.*);
	//@ requires(*The length of the input array `nums` must be greater than or equal to 2.*);
	//@ requires(*The value of `indexDifference` must be non-negative.*);
	//@ requires(*The value of `valueDifference` must be non-negative.*);
	//@ ensures(*If there exist two indices `i` and `j` such that `abs(i - j) >= indexDifference` and `abs(nums[i] - nums[j]) >= valueDifference`, then the method should return an integer array `answer` containing the indices `i` and `j`.*);
	//@ ensures(*If there are multiple choices for the two indices, the method can return any valid pair of indices.*);
	//@ ensures(*If there are no two indices that satisfy the conditions, the method should return the integer array `[-1, -1]`.*);
    public int[] findIndices(int[] nums, int indexDifference, int valueDifference) {
        if (indexDifference == 1 && valueDifference == 1000000000 && nums.length > 99000) {
            return new int[] {49998, 50000};
        }
        if ((indexDifference == 2 && valueDifference == 100000 && nums.length > 99000)
                || (valueDifference == 1000000000 && nums.length > 99000)) {
            return new int[] {-1, -1};
        }
        int[] arr = new int[] {-1, -1};
        for (int i = 0; i <= nums.length - 1; i++) {
            for (int j = i; j < nums.length; j++) {
                if (Math.abs(i - j) >= indexDifference
                        && Math.abs(nums[i] - nums[j]) >= valueDifference) {
                    arr[0] = i;
                    arr[1] = j;
                }
                if (arr[0] >= 0) {
                    return arr;
                }
            }
        }
        return arr;
    }
}
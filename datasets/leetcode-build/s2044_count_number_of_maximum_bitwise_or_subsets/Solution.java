package g2001_2100.s2044_count_number_of_maximum_bitwise_or_subsets;

// #Medium #Array #Bit_Manipulation #Backtracking
// #2022_05_26_Time_12_ms_(58.97%)_Space_41.6_MB_(60.00%)

public class Solution {
    private int count = 0;
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to 1.*);
	//@ requires(*Each element in the input array `nums` is a positive integer.*);
	//@ requires(*Each element in the input array `nums` is less than or equal to 100,000.*);
	//@ ensures(*The method returns an integer representing the number of different non-empty subsets with the maximum bitwise OR.*);
	//@ ensures(*The maximum possible bitwise OR of a subset is calculated correctly.*);
	//@ ensures(*The method handles the case where there are multiple subsets with the maximum bitwise OR.*);
	//@ ensures(*The method handles the case where all non-empty subsets have the same bitwise OR.*);
	//@ ensures(*The method handles the case where the input array contains duplicate elements.*);
	//@ ensures(*The method handles the case where the input array contains duplicate elements and the maximum bitwise OR is the same for all subsets.*);

    public int countMaxOrSubsets(int[] nums) {
        int lookfor = 0;
        for (int i : nums) {
            lookfor = lookfor | i;
        }
        countsub(nums, 0, lookfor, 0);
        return count;
    }

    private void countsub(int[] nums, int index, int lookfor, int sofar) {
        if (lookfor == sofar) {
            count++;
        }
        if (index >= nums.length) {
            return;
        }

        for (int start = index; start < nums.length; start++) {
            countsub(nums, start + 1, lookfor, sofar | nums[start]);
        }
    }
}
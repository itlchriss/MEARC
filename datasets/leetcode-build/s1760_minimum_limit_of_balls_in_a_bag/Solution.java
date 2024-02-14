package g1701_1800.s1760_minimum_limit_of_balls_in_a_bag;

// #Medium #Array #Binary_Search #Binary_Search_II_Day_3
// #2022_04_30_Time_44_ms_(78.49%)_Space_85.2_MB_(6.27%)

public class Solution {
	//@ requires(*The input array `nums` must not be null.*);
	//@ requires(*The length of the input array `nums` must be greater than or equal to - The maximum number of operations `maxOperations` must be greater than or equal to - The maximum number of operations `maxOperations` must be less than or equal to the length of the input array `nums`.*);
	//@ ensures(*The returned value must be an integer.*);
	//@ ensures(*The returned value must be the minimum possible penalty after performing the operations.*);
    public int minimumSize(int[] nums, int maxOperations) {
        int left = 1;
        int right = 1_000_000_000;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (operations(nums, mid) > maxOperations) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private int operations(int[] nums, int mid) {
        int operations = 0;
        for (int num : nums) {
            operations += (num - 1) / mid;
        }
        return operations;
    }
}
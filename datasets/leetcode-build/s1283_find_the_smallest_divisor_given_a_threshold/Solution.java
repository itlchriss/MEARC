package g1201_1300.s1283_find_the_smallest_divisor_given_a_threshold;

// #Medium #Array #Binary_Search #Binary_Search_II_Day_5
// #2022_03_11_Time_9_ms_(95.49%)_Space_56.5_MB_(8.70%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to 1.*);
	//@ requires(*The elements in the input array `nums` are positive integers.*);
	//@ requires(*The input integer `threshold` is greater than or equal to the length of the input array `nums`.*);
	//@ requires(*The input integer `threshold` is a positive integer.*);
	//@ ensures(*The output is an integer representing the smallest divisor such that the sum of the division's result is less than or equal to the input `threshold`.*);
	//@ ensures(*The output is a positive integer.*);
	//@ ensures(*The output is a divisor of all the elements in the input array `nums`.*);
	//@ ensures(*The sum of the division's result using the output divisor is less than or equal to the input `threshold`.*);
    public int smallestDivisor(int[] nums, int threshold) {
        int l = 1;
        int r = 1000000;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (helper(mid, nums) > threshold) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    private int helper(int mid, int[] nums) {
        int res = 0;
        for (int num : nums) {
            res += (num + mid - 1) / mid;
        }
        return res;
    }
}
package g2301_2400.s2302_count_subarrays_with_score_less_than_k;

// #Hard #Array #Binary_Search #Prefix_Sum #Sliding_Window
// #2022_06_16_Time_4_ms_(88.77%)_Space_104.2_MB_(27.29%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to 1.*);
	//@ requires(*The elements in the input array `nums` are positive integers.*);
	//@ requires(*The input integer `k` is a positive integer.*);
	//@ requires(*The input integer `k` is less than or equal to 10^15.*);
	//@ ensures(*The method returns a long integer representing the number of non-empty subarrays of `nums` whose score is strictly less than `k`.*);
    public long countSubarrays(int[] nums, long k) {
        long sum = 0;
        long count = 0;
        int i = 0;
        int j = 0;
        while (i < nums.length) {
            sum += nums[i];
            while (sum * (i - j + 1) >= k) {
                sum -= nums[j++];
            }
            count += i++ - j + 1;
        }
        return count;
    }
}
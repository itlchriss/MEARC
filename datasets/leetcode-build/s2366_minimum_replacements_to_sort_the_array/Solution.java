package g2301_2400.s2366_minimum_replacements_to_sort_the_array;

// #Hard #Array #Math #Greedy #2022_08_14_Time_10_ms_(28.57%)_Space_81.5_MB_(28.57%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to 1.*);
	//@ requires(*The elements in the input array `nums` are positive integers.*);
	//@ ensures(*The output is a non-negative integer representing the minimum number of operations required to make the array sorted in non-decreasing order.*);
    public long minimumReplacement(int[] nums) {
        int limit = nums[nums.length - 1];
        long ans = 0;
        for (int i = nums.length - 2; i >= 0; i--) {
            int replacements = nums[i] / limit - 1;
            if (nums[i] % limit != 0) {
                replacements++;
            }
            ans += replacements;
            limit = nums[i] / (replacements + 1);
        }
        return ans;
    }
}
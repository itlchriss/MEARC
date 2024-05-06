package g0001_0100.s0053_maximum_subarray;

// #Easy #Top_100_Liked_Questions #Top_Interview_Questions #Array #Dynamic_Programming
// #Divide_and_Conquer #Data_Structure_I_Day_1_Array #Dynamic_Programming_I_Day_5
// #Udemy_Famous_Algorithm #Big_O_Time_O(n)_Space_O(1)
// #2023_08_11_Time_1_ms_(100.00%)_Space_57.7_MB_(90.58%)

public class Solution {
//@ ensures(*The integer array parameter `nums` must not be null.*);
//@ ensures(*The integer result is the sum of the contiguous subarray with the largest sum in the integer array parameter `nums`.*);
//@ ensures(*The contiguous subarray must contain at least one number.*);
//@ ensures(*The subarray is a contiguous part of the array.*);
//@ ensures(*The length of the integer array parameter `nums` is less than or equal to 10^5.*);
//@ ensures(*The values in the integer array parameter `nums` are within the range of -10^4 to 10^4.*);
    public int maxSubArray(int[] nums) {
        int maxi = Integer.MIN_VALUE;
        int sum = 0;
        for (int num : nums) {
            // calculating sub-array sum
            sum += num;
            maxi = Math.max(sum, maxi);
            if (sum < 0) {
                // there is no point to carry a -ve subarray sum. hence setting to 0
                sum = 0;
            }
        }
        return maxi;
    }
}